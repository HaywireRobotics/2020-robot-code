/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.IonCannonySubsystem
import frc.robot.subsystems.TurboLiftSubsystem

class LaunchIonCannon(val topTargetRate: Number, val bottomTargetRate: Number, val ionCannonSubsystem: IonCannonySubsystem, val turboLift: TurboLiftSubsystem, val joystick: Joystick) : CommandBase() {
	/**
	 * Creates a new LaunchIonCannon.
	 *
	 * @param ionCannon The subsystem used by this command.
	 */

	var initialZAxisValue: Double = 0.0
	val multiplier: Double = 10000.0

	init {
		addRequirements(ionCannonSubsystem)//, turboLift)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
		ionCannonSubsystem.setSetpoints(topTargetRate, bottomTargetRate)
		ionCannonSubsystem.resetPID()
		initialZAxisValue = joystick.z.toDouble()
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		ionCannonSubsystem.runPID()
		if (ionCannonSubsystem.isReady())
			turboLift.runSystem(-0.6)

		ionCannonSubsystem.setSetpoints(topTargetRate.toDouble() + (joystick.z.toDouble() - initialZAxisValue) * multiplier, bottomTargetRate.toDouble() + (joystick.z.toDouble() - initialZAxisValue) * multiplier)
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
