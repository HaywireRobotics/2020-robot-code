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
import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType

import frc.robot.Constants

class HyperdriveSubsystem : SubsystemBase() {
  /**
   * Creates a new HyperdriveSubsystem.
   */
  private val leftFront: CANSparkMax = CANSparkMax(Constants.Hyperdrive.leftFrontPort, MotorType.kBrushless)
  private val leftBack: CANSparkMax = CANSparkMax(Constants.Hyperdrive.leftBackPort, MotorType.kBrushless)
  private val rightFront: CANSparkMax = CANSparkMax(Constants.Hyperdrive.rightFrontPort, MotorType.kBrushless)
  private val rightBack: CANSparkMax = CANSparkMax(Constants.Hyperdrive.rightBackPort, MotorType.kBrushless)
  
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
