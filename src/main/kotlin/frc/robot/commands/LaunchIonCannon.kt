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
import edu.wpi.first.wpilibj.Joystick

class LaunchIonCannon(val topTargetRate: Number, val bottomTargetRate: Number, val ionCannon: IonCannony, val turboLift: TurboLiftSubsystem, val joystick: Joystick) : CommandBase() {
  /**
   * Creates a new LaunchIonCannon.
   *
   * @param ionCannon The subsystem used by this command.
   */

  var initialZAxisValue: Double = 0.0
  val multiplier: Double = 10000.0

  init {
    addRequirements(ionCannon)//, turboLift)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
    ionCannon.setSetpoints(topTargetRate, bottomTargetRate)
    ionCannon.resetPID()
    initialZAxisValue = joystick.getZ().toDouble()
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    ionCannon.runPID()
    if (ionCannon.isReady())
      turboLift.runSystem(-0.6)

    ionCannon.setSetpoints(topTargetRate.toDouble() + (joystick.getZ().toDouble() - initialZAxisValue) * multiplier, bottomTargetRate.toDouble() + (joystick.getZ().toDouble() - initialZAxisValue) * multiplier)
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
