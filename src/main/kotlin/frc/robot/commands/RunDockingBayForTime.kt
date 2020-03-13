/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.DockingBaySubsystem

class RunDockingBayForTime(val m_subsystem: DockingBaySubsystem, private val speed: Number, val time: Number) : CommandBase() {
	/**
	 * Creates a new RunDockingBayForTime.
	 *
	 * @param m_subsystem The subsystem used by this command.
	 */

	private val timer: Timer = Timer()

	init {
		addRequirements(m_subsystem)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
		timer.reset()
		timer.start()
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		m_subsystem.motor.set(speed.toDouble())
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
		m_subsystem.motor.set(0.0)
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return timer.hasPeriodPassed(time.toDouble())
	}
}
