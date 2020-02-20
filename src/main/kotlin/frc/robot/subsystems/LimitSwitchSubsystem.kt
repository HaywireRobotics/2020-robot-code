/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.DigitalInput

class LimitSwitchSubsystem : SubsystemBase() {
  /**
   * Creates a new LimitSwitchSubsystem.
   */
   val switch0: DigitalInput = DigitalInput(0)
   val switch1: DigitalInput = DigitalInput(1)
  init {
  }

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }
  fun getSwitch0() = switch0.get()
  fun getSwitch1() = switch1.get()
}
