/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.TurboLiftSubsystem

class TurboLiftDefault(val m_subsystem: TurboLiftSubsystem, private val joystick: Joystick) : CommandBase() {
	/**
	 * Creates a new TurboLiftSubsystemDefault.
	 *
	 * @param m_subsystem The subsystem used by this command.
	 */

	private var joystickPower: Double = 0.0

	init {
		addRequirements(m_subsystem)
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		// if (Math.abs(joystick.getY()) > 0.1 || Math.abs(frontJoystick.getY()) > 0.1) {
		// m_subsystem.agiTater.set(agiSpeed)
		// m_subsystem.frontMotor.set(frontJoystick.getY())
		// m_subsystem.backMotor.set(joystick.getY())
		// }
		joystickPower = joystick.y
		// println(joystickPower)
		m_subsystem.runSystem(joystickPower)
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
		m_subsystem.runSystem(0.0)

	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}
}
