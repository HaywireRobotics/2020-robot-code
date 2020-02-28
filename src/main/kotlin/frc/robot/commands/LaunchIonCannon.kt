/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.IonCannony
import frc.robot.subsystems.TurboLiftSubsystem

import edu.wpi.first.wpilibj2.command.CommandBase

class LaunchIonCannon(val topTargetRate: Number, val bottomTargetRate: Number, val ionCannon: IonCannony, val turboLift: TurboLiftSubsystem) : CommandBase() {
  /**
   * Creates a new LaunchIonCannon.
   *
   * @param ionCannon The subsystem used by this command.
   */

  init {
    addRequirements(ionCannon, turboLift)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
    ionCannon.setSetpoints(topTargetRate, bottomTargetRate)
    ionCannon.resetPID()
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    ionCannon.runPID()
    turboLift.runSystem(0.4)
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    ionCannon.endPID()
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
