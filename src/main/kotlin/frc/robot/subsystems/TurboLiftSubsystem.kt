/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import frc.robot.Constants

import edu.wpi.first.wpilibj2.command.SubsystemBase

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX

class TurboLiftSubsystem : SubsystemBase() {
  
  val backMotor: WPI_VictorSPX = WPI_VictorSPX(Constants.TurboLift.backMotorPort)
  val frontMotor: WPI_VictorSPX = WPI_VictorSPX(Constants.TurboLift.frontMotorPort)
  val agitator: WPI_VictorSPX = WPI_VictorSPX(Constants.DockingBay.agitatorPort)

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }

  fun runSystem(elevatorSpeed: Double) {
    var agitatorSpeed = 0.4
    if (elevatorSpeed == 0.0){
      agitatorSpeed = 0.0
    }
    backMotor.set(elevatorSpeed)
    frontMotor.set(elevatorSpeed)
    agitator.set(agitatorSpeed)
  }
}
