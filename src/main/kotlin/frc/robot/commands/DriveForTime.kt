/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.HyperdriveSubsystem
import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.Timer

class DriveForTime(val m_subsystem: HyperdriveSubsystem, val speed: Double, val time: Double) : CommandBase() {
  /**
   * Creates a new DriveForTime.
   *
   * @param m_subsystem The subsystem used by this command.
   */
  private val timer : Timer = Timer()
  
  init {
    addRequirements(m_subsystem)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
    timer.reset()
    timer.start()
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    m_subsystem.tankDrive(speed, speed)
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    m_subsystem.tankDrive(0.0, 0.0)
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return timer.hasPeriodPassed(time)
  }
}
