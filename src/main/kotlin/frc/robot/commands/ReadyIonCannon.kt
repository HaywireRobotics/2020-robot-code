/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.IonCannonySubsystem

class ReadyIonCannon(private val ionCannon: IonCannonySubsystem) : CommandBase() {
	/**
	 * Creates a new ReadyIonCannon.
	 *
	 * @param ionCannon The subsystem used by this command.
	 */
	init {
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
      ionCannon.setSetpoints(100000, 100000)
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}
}
