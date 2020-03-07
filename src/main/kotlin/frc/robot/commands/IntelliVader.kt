/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.TurboLiftSubsystem

class IntelliVader(val m_subsystem: TurboLiftSubsystem) : CommandBase() {
	/**
	 * Creates a new IntelliVader.
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
		//if sensor0 sees ball:
		// move elevator till sensor1 sees ball
		// if sensor1 sees ball:
		// move elevator till sensor2 sees ball
		// if sensor2 sees ball:
		// move elevevator till sensor3 sees ball
		// if sensor3 sees ball:
		// move elevevator till sensor1 sees ball
		// if sensor4 sees ball:
		// stop this whole "if" armada

		//Figure out furthest sensor that is triggered
		//Run motor(s) until next sensor is triggered
		//  Then stop motor(s)
		//Cover special case of ball being at end of snake

		//run motor until not (sensor0 or sensor1)


		m_subsystem.frontMotor.set(.25)
		m_subsystem.backMotor.set(.25)
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}
}
