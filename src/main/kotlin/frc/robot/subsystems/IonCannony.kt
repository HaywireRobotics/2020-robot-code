/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase

import edu.wpi.first.wpilibj.PWMVictorSPX

import frc.robot.Constants

class IonCannony : SubsystemBase() {
  /**
   * Creates a new IonCannony.
   */
  val top: PWMVictorSPX = PWMVictorSPX(Constants.IonCannony.topPort)
  val bottom: PWMVictorSPX = PWMVictorSPX(Constants.IonCannony.bottomPort)
  
  init {
  }

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }
}
