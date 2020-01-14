/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.PWMVictorSPX
import edu.wpi.first.wpilibj.SpeedControllerGroup 
import frc.robot.Constants

class DrivetrainSubsystem : SubsystemBase() {
  /**
   * Creates a new DrivetrainSubsystem.
   */
  private val leftFornt: PWMVictorSPX = PWMVictorSPX(Constants.driveLeftFrontPort)
  private val leftBack: PWMVictorSPX = PWMVictorSPX(Constants.driveLeftBackPort)
  private val rightFornt: PWMVictorSPX = PWMVictorSPX(Constants.driveRightFrontPort)
  private val rightBack: PWMVictorSPX = PWMVictorSPX(Constants.driveRightBackPort)
  
  private val myRobot = DifferentialDrive(SpeedControllerGroup(leftFornt, leftBack), SpeedControllerGroup(rightFornt, rightBack ) )

  init {
  }

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }

fun tankDrive(leftPower: Double, rightPower: Double) = myRobot.tankDrive(leftPower,rightPower, false) 
}
