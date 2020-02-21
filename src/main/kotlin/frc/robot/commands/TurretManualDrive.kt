/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.TurretSubsystem

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.Joystick

class TurretManualDrive(val m_subsystem: TurretSubsystem, val joystick: Joystick) : CommandBase() {
  /**
   * Creates a new TurretManualDrive.
   *
   * @param m_subsystem The subsystem used by this command.
   */
  init {
    addRequirements(m_subsystem)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    m_subsystem.motor.set(joystick.getX() / 3)
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
