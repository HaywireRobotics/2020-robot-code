/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.TurboLiftSubsystem
import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.Joystick

class TurboLiftyDefault(val m_subsystem: TurboLiftSubsystem, val backJoystick: Joystick, val frontJoystick: Joystick) : CommandBase() {
  /**
   * Creates a new TurboLiftySubsystemDefault.
   *
   * @param m_subsystem The subsystem used by this command.
   */
     var agiSpeed = 0.7

  init {
    addRequirements(m_subsystem)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    if (Math.abs(backJoystick.getY()) > 0.05 || Math.abs(frontJoystick.getY()) > 0.05) {
      m_subsystem.backMotor.set(backJoystick.getY())
      m_subsystem.frontMotor.set(frontJoystick.getY())
      m_subsystem.agiTater.set(agiSpeed)
    }
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
        m_subsystem.agiTater.set(0.0)

  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
