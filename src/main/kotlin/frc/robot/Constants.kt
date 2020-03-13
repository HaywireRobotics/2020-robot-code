/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
class Constants {
	companion object {
		// Put Constants inside the companion object to make them globally accessible.
	}

	class Joysticks {
		companion object {
			const val driverRightPort: Int = 0
			const val driverLeftPort: Int = 1
			const val manipulatorRightPort: Int = 2
			const val manipulatorLeftPort: Int = 3
		}
	}

	class DockingBay {
		companion object {
			const val intakePort: Int = 50
			const val agitatorPort: Int = 1
		}
	}

	class Hyperdrive {
		companion object {
			const val rightFrontPort: Int = 3
			const val rightBackPort: Int = 6

			const val leftFrontPort: Int = 1
			const val leftBackPort: Int = 2
		}
	}

	class TurboLift {
		companion object {
			const val backMotorPort: Int = 2
			const val frontMotorPort: Int = 40
		}
	}

	class ControlPanel {
		companion object {
			const val colorMotor: Int = 11
		}
	}

	class IonCannon {
		companion object {
			// Motors
			const val topPort: Int = 0
			const val bottomPort: Int = 3

			// Encoders
			const val topEncoderAPort: Int = 7
			const val topEncoderBPort: Int = 6

			const val bottomEncoderAPort: Int = 9
			const val bottomEncoderBPort: Int = 8
		}
	}

	class Climb {
		companion object {
			const val hookPort: Int = 12
			const val winchPort: Int = 5
		}
	}

	class Turret {
		companion object {
			// Motors
			const val motorPort: Int = 4
		}
	}
}
