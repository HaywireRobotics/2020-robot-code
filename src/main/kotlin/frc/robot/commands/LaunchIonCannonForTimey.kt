/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.IonCannonySubsystem
import frc.robot.subsystems.TurboLiftSubsystem

class LaunchIonCannonForTimey(val topTargetRate: Number, val bottomTargetRate: Number, val shootTime: Number, val ionCannonSubsystem: IonCannonySubsystem, val turboLift: TurboLiftSubsystem) : CommandBase() {
	/**
	 * Creates a new LaunchIonCannonForTimey.
	 *
	 * @param m_subsystem The subsystem used by this command.
	 */
	private val timer: Timer = Timer()

	init {
		addRequirements(ionCannonSubsystem, turboLift)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
		timer.reset()
		timer.start()
		ionCannonSubsystem.setSetpoints(topTargetRate, bottomTargetRate)
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
		turboLift.runSystem(0.0)
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return timer.hasPeriodPassed(shootTime.toDouble())
	}
}
