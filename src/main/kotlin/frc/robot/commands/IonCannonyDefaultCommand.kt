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

class IonCannonyDefaultCommand(val m_subsystem: IonCannonySubsystem, val topJoystick: Joystick, val bottomJoystick: Joystick) : CommandBase() {
	/**
	 * Creates a new IonCannonyDefaultCommand.
	 *
	 * @param m_subsystem The subsystem used by this command.
	 */
	init {
		addRequirements(m_subsystem)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		m_subsystem.top.set(topJoystick.y)
		m_subsystem.bottom.set(bottomJoystick.y)
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}
}
