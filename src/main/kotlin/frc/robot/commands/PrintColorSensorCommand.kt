/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.ColorSensorSubsystem
import edu.wpi.first.wpilibj2.command.CommandBase
//import frc.robot

class PrintColorSensorCommand(val m_subsystem: ColorSensorSubsystem) : CommandBase() {
  /**
   * Creates a new PrintColorSensorCommand.
   *
   */
  init {
    addRequirements(m_subsystem)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    var color = m_subsystem.getColorRGB()
    println(color.red)
    println(color.green)
    println(color.blue)
    println()
    }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
