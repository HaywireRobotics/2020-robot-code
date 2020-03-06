/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.commands.DriveForTime
import frc.robot.commands.LaunchIonCannonForTimey
import frc.robot.commands.TurretSeekAutonomous
import frc.robot.commands.RunDockingBayForTime

import frc.robot.subsystems.IonCannony
import frc.robot.subsystems.TurretSubsystem
import frc.robot.subsystems.HyperdriveSubsystem
import frc.robot.subsystems.DockingBaySubsystem
import frc.robot.subsystems.TurboLiftSubsystem

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
class TrenchRunPickupAuto(ionCannon: IonCannony, turret: TurretSubsystem, turboLift: TurboLiftSubsystem, dockingBay: DockingBaySubsystem, drivetrain: HyperdriveSubsystem) : SequentialCommandGroup() {
  init {
    // Add your commands in the super() call, e.g.
    // super(FooCommand(), BarCommand())
    addCommands(
      TurretSeekAutonomous(turret),
      LaunchIonCannonForTimey(155000, 155000, 7, ionCannon, turboLift),
      ParallelCommandGroup(
        RunDockingBayForTime(dockingBay, -0.7, 15),
        DriveForTime(drivetrain, -0.25, 0.75)
      )
    )
  }
}