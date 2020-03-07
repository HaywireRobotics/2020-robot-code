/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import frc.robot.subsystems.*

class TrenchRunPickupAuto(ionCannonSubsystem: IonCannonySubsystem, turret: TurretSubsystem, turboLift: TurboLiftSubsystem, dockingBay: DockingBaySubsystem, driveTrain: HyperdriveSubsystem) : SequentialCommandGroup() {
	init {
		// Add your commands in the super() call, e.g.
		// super(FooCommand(), BarCommand())
		addCommands(
			TurretSeekAutonomous(turret),
			LaunchIonCannonForTimey(160000, 160000, 7, ionCannonSubsystem, turboLift),
			ParallelCommandGroup(
				RunDockingBayForTime(dockingBay, -0.7, 15),
				DriveForTime(driveTrain, -0.25, 2.25)
			)
		)
	}
}
