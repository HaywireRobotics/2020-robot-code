/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.HyperdriveSubsystem
import edu.wpi.first.wpilibj2.command.CommandBase

class SwitchDriveDirection(val m_subsystem: HyperdriveSubsystem) : CommandBase() {
  /**
   * Creates a new SwitchDriveDirection.
   *
   * @param m_subsystem The subsystem used by this command.
   */

  // Called when the command is initially scheduled.
  override fun initialize() {
    m_subsystem.robotDirectionInverted = when (m_subsystem.robotDirectionInverted) {
      false -> true
      true -> false
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
