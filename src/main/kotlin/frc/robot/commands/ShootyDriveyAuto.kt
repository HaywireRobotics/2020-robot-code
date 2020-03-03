/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.CommandBase
import edu.frc.robot.commands.TurretSeek
import edu.frc.robot.commands.DriveForTime
import edu.frc.robot.commands.LaunchIonCannonForTimey
import edu.frc.robot.subsystems.TurretSubsystem
import edu.frc.robot.subsystems.TurboLiftSubsystem
import edu.frc.robot.subsystems.HyperdriveSubsystem
import edu.frc.robot.subsystems.IonCannony
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
class ShootyDriveyAuto(turret: TurretSubsystem, hyperDrive: HyperdriveSubsystem, ionCannon: IonCannony, turboLift: TurboLiftSubsystem) : SequentialCommandGroup() {
  init {

    
    // Add your commands in the super() call, e.g.
    // super(FooCommand(), BarCommand())
    super()
  }
}
