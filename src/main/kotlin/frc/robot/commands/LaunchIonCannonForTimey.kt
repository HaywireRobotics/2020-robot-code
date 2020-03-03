a/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.IonCannony
import frc.robot.subsystems.TurboLiftSubsystem

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.Timer

class LaunchIonCannonForTimey(val topTargetRate: Number, val bottomTargetRate: Number, val ionCannon: IonCannony, val turboLift: TurboLiftSubsystem) : CommandBase() {
  /**
   * Creates a new LaunchIonCannonForTimey.
   *
   * @param m_subsystem The subsystem used by this command.
   */
   private val timer : Timer = Timer()
  init {
    addRequirements(ionCannon, turboLift)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
    timer.reset()
    timer.start()
    ionCannon.setSetpoints(topTargetRate, bottomTargetRate)
    ionCannon.resetPID()
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {

        ionCannon.runPID()
    if (ionCannon.isReady())
      turboLift.runSystem(-0.6)

  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    ionCannon.endPID()
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
      return timer.hasPeriodPassed(10)
  }
}