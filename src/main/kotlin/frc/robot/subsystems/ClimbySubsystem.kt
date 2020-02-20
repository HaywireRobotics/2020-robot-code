/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import frc.robot.Constants


class ClimbySubsystem : SubsystemBase() {
  /**
   * Creates a new ClimbySubsystem.
   */
  val hookMotor: WPI_VictorSPX = WPI_VictorSPX(Constants.Climby.hookPort)
  val winchMotor: WPI_VictorSPX = WPI_VictorSPX(Constants.Climby.winchPort)

  init {
  }

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }
  fun driveHookMotor(power: Double) = hookMotor.set(power)
  fun driveWinchMotor(power: Double) = winchMotor.set(power)
}
