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
      val driverRightPort: Int = 0
      val driverLeftPort: Int = 1
      val manipulatorRightPort: Int = 2
      val manipulatorLeftPort: Int = 3
    }
  }

  class DockingBay {
    companion object {
      val motorPort: Int = 0
    }
  }

  class Hyperdrive {
    companion object {
      val rightFrontPort : Int = 3
      val rightBackPort : Int = 2

      val leftFrontPort : Int = 1
      val leftBackPort : Int = 0
    }
  }

  class TurboLift {
    companion object {
      val backMotorPort: Int = 4
      val frontMotorPort: Int = 5
    }
  }

  class IonCannony {
    companion object {
      val topPort: Int = 1
      val bottomPort: Int = 2
    }
  }
}
