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
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.IonCannonSubsystem
import frc.robot.subsystems.TurboLiftSubsystem

class TestShooterNT(private val ionCannonSubsystem: IonCannonSubsystem, private val turboLift: TurboLiftSubsystem) : CommandBase() {
	/**
	 * Creates a new TestShooterNT.
	 *
	 * @param ionCannon The subsystem used by this command.
	 */

	private val nt: NetworkTableInstance = NetworkTableInstance.getDefault()
	private val table: NetworkTable
	private val topShooterSpeedEntry: NetworkTableEntry
	private val bottomShooterSpeedEntry: NetworkTableEntry

	private var topTargetRate: Double = 0.0
	private var bottomTargetRate: Double = 0.0

	init {
		addRequirements(ionCannonSubsystem)

		table = nt.getTable("datatable")
		topShooterSpeedEntry = table.getEntry("topShooterSpeed")
		bottomShooterSpeedEntry = table.getEntry("bottomShooterSpeed")

		nt.startClientTeam(1569)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
		topTargetRate = topShooterSpeedEntry.getDouble(topTargetRate)
		bottomTargetRate = bottomShooterSpeedEntry.getDouble(bottomTargetRate)

		println("TOP Setpoint: $topTargetRate")
		println("BOTTOM Setpoint: $bottomTargetRate")

		ionCannonSubsystem.setSetpoints(bottomTargetRate, topTargetRate)
		ionCannonSubsystem.resetPID()
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		ionCannonSubsystem.runPID()
		if (ionCannonSubsystem.isReady())
			turboLift.runSystem(-0.6)
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
		ionCannonSubsystem.endPID()
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}
}
