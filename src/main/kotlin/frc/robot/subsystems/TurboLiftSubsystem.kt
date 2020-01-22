/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import frc.robot.Constants

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.Spark

class TurboLiftSubsystem : SubsystemBase() {
  
  val backMotor: Spark = Spark(Constants.TurboLift.backMotorPort)
  val frontMotor: Spark = Spark(Constants.TurboLift.frontMotorPort)

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }
}
