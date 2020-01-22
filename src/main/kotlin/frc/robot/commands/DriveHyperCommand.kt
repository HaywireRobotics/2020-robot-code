/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.HyperdriveSubsystem

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.GenericHID.Hand

class DriveHyperCommand(val hyperdriveSubsystem: HyperdriveSubsystem, val leftJoystick: Joystick, val rightJoystick: Joystick) : CommandBase() {
  /**
   * Creates a new DriveHyperCommand.
   *
   * @param hyperdriveSubsystem The subsystem used by this command.
   */
  init {
    addRequirements(hyperdriveSubsystem)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    hyperdriveSubsystem.tankDrive(leftJoystick.getY(), rightJoystick.getY())
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    hyperdriveSubsystem.tankDrive(0.0,0.0)
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
