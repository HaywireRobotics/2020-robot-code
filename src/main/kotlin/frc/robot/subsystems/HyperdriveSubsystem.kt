/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.SpeedControllerGroup

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX

import frc.robot.Constants

class HyperdriveSubsystem : SubsystemBase() {
  /**
   * Creates a new HyperdriveSubsystem.
   */
  private val leftFront: WPI_VictorSPX = WPI_VictorSPX(Constants.Hyperdrive.leftFrontPort)
  private val leftBack: WPI_VictorSPX = WPI_VictorSPX(Constants.Hyperdrive.leftBackPort)
  private val rightFront: WPI_VictorSPX = WPI_VictorSPX(Constants.Hyperdrive.rightFrontPort)
  private val rightBack: WPI_VictorSPX = WPI_VictorSPX(Constants.Hyperdrive.rightBackPort)
  
  private val myRobot = DifferentialDrive(SpeedControllerGroup(leftFront, leftBack), SpeedControllerGroup(rightFront, rightBack))

  init {
  }

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }

fun tankDrive(leftPower: Double, rightPower: Double) = myRobot.tankDrive(leftPower, rightPower, false)
}
