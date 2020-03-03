/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.commands.TurretSeek
import frc.robot.commands.DriveForTime
import frc.robot.commands.LaunchIonCannonForTimey
import frc.robot.subsystems.TurretSubsystem
import frc.robot.subsystems.TurboLiftSubsystem
import frc.robot.subsystems.HyperdriveSubsystem
import frc.robot.subsystems.IonCannony
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
class ShootyDriveyAuto(turret: TurretSubsystem, hyperDrive: HyperdriveSubsystem, ionCannon: IonCannony, turboLift: TurboLiftSubsystem) : SequentialCommandGroup() {
  init {
    // Add your commands in the super() call, e.g.
    // super(FooCommand(), BarCommand())
    addCommands(TurretSeek(turret), LaunchIonCannonForTimey(150000, 150000, 10, ionCannon, turboLift), DriveForTime(hyperDrive, 0.3, 0.5))
  }
}
