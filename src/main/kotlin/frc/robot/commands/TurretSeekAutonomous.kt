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
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.controller.PIDController
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.TurretSubsystem

class TurretSeekAutonomous(val m_subsystem: TurretSubsystem) : CommandBase() {
	/**
	 * Creates a new turretSeek.kt.
	 *
	 * @param m_subsystem The subsystem used by this command.
	 */

	val pidController: PIDController

	val nt: NetworkTableInstance = NetworkTableInstance.getDefault()
	val table: NetworkTable
	val angleEntry: NetworkTableEntry

	val marginOfError: Double = 1.0

	private val timer: Timer = Timer()

	init {
		addRequirements(m_subsystem)

		//pidController = PIDController(0.1, 0.0, 0.0)
		pidController = PIDController(0.025, 0.008, 0.0025)
		table = nt.getTable("datatable")
		angleEntry = table.getEntry("Angle")

		nt.startClientTeam(1569)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
		pidController.reset()

		timer.reset()
		timer.start()
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		useOutput(pidController.calculate(generateMeasurement(), generateSetpoint()))
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
		useOutput(0.0)
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		val angle = angleEntry.getDouble(0.0)

		return (angle < marginOfError && angle > -marginOfError) || timer.hasPeriodPassed(2.0)
	}

	fun useOutput(output: Double) = m_subsystem.motor.set(1 * output)

	fun generateMeasurement(): Double {
		return angleEntry.getDouble(0.0)
	}

	fun generateSetpoint(): Double {
		return 0.0
	}
}
