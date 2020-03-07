/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.TurretSubsystem

class TurretToAngle(val m_subsystem: TurretSubsystem) : CommandBase() {
	/**
	 * Creates a new TurretToAngle.
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
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}
}
