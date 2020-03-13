/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj.controller.PIDController
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.TurretSubsystem

class TurretSeekLimelight(val m_subsystem: TurretSubsystem) : CommandBase() {
	/**
	 * Creates a new turretSeek.kt.
	 *
	 * @param m_subsystem The subsystem used by this command.
	 */

	private val pidController: PIDController

	private val nt: NetworkTableInstance = NetworkTableInstance.getDefault()
	private val table: NetworkTable
	private val tx: NetworkTableEntry
	private val light: NetworkTableEntry

	private var waitCounter: Int = 0

	init {
		addRequirements(m_subsystem)

		//pidController = PIDController(0.1, 0.0, 0.0)
		pidController = PIDController(0.025, 0.008, 0.0025)
		table = nt.getTable("limelight")
		tx = table.getEntry("tx")
		light = table.getEntry("ledMode")

		nt.startClientTeam(1569)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
		pidController.reset()
		light.setNumber(3)
		waitCounter = 20
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		if (waitCounter > 0)
			waitCounter -= 1
		else
			useOutput(pidController.calculate(generateMeasurement(), generateSetpoint()))
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
		useOutput(0.0)
		light.setNumber(0)
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}

	private fun useOutput(output: Double) = m_subsystem.motor.set(-output)

	private fun generateMeasurement(): Double {
		return tx.getDouble(0.0)
	}

	private fun generateSetpoint(): Double {
		return (0.0)
	}
}
