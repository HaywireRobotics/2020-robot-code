/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.Constants

class TurretSubsystem : SubsystemBase() {
	/**
	 * Creates a new TurretSubsystem.
	 */

	val motor: WPI_VictorSPX = WPI_VictorSPX(Constants.Turret.motorPort)

	init {
	}

	/**
	 * Will be called periodically whenever the CommandScheduler runs.
	 */
	override fun periodic() {
	}
}
