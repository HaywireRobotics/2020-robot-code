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
import kotlin.math.abs

class TurboLiftSubsystem : SubsystemBase() {

	val backMotor: WPI_VictorSPX = WPI_VictorSPX(Constants.TurboLift.backMotorPort)
	val frontMotor: WPI_VictorSPX = WPI_VictorSPX(Constants.TurboLift.frontMotorPort)
	private val agitator: WPI_VictorSPX = WPI_VictorSPX(Constants.DockingBay.agitatorPort)

	private val maxPulseCount: Int = 100
	private var pulseCount: Int = 0

	private var agitatorSpeed = -0.085

	/**
	 * Will be called periodically whenever the CommandScheduler runs.
	 */
	override fun periodic() {
	}

	fun runSystem(elevatorSpeed: Double) {
//		if (pulseCount >= maxPulseCount) {
//			agitatorSpeed *= -1
//			pulseCount = 0
//		}
//		pulseCount += 1

		if (abs(elevatorSpeed) > 0.1) {
			frontMotor.set(-elevatorSpeed)
			backMotor.set(elevatorSpeed)
			agitator.set(agitatorSpeed)
		} else {
			frontMotor.set(0.0)
			backMotor.set(0.0)
			agitator.set(0.0)
		}
	}
}
