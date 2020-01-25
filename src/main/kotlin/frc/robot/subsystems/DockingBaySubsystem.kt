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

class DockingBaySubsystem : SubsystemBase() {
  
  val motor: Spark = Spark(Constants.DockingBay.motorPort)

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }
}