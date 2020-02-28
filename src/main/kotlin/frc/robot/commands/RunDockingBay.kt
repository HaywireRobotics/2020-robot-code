/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.DockingBaySubsystem
import edu.wpi.first.wpilibj.PowerDistributionPanel
import edu.wpi.first.wpilibj2.command.CommandBase

class RunDockingBay(val m_subsystem: DockingBaySubsystem, val pdp: PowerDistributionPanel) : CommandBase() {
  /**
   * Creates a new RunDockingBay.
   *
   * @param m_subsystem The subsystem used by this command.
   */
  init {
    addRequirements(m_subsystem)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
    //agiSpeed = -1.0 * agiSpeed
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    m_subsystem.motor.set(-0.7)
    // println(pdp.getCurrent(5))
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    m_subsystem.motor.set(0.0)
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
