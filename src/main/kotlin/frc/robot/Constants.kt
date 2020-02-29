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
      val intakePort : Int = 50
      val agitatorPort : Int = 1
    }
  }

  class Hyperdrive {
    companion object {
      val rightFrontPort : Int = 3
      val rightBackPort : Int = 6

      val leftFrontPort : Int = 1
      val leftBackPort : Int = 2
    }
  }

  class TurboLift {
    companion object {
      val backMotorPort: Int = 2
      val frontMotorPort: Int = 40
    }
  }
  class ControlPanel {
    companion object {
      val colorMotor: Int = 11
    }
  }
  class IonCannony {
    companion object {
      // Motors
      val topPort: Int = 0
      val bottomPort: Int = 3

      // Encoders
      val topEncoderAPort: Int = 7
      val topEncoderBPort: Int = 6
      
      val bottomEncoderAPort: Int = 9
      val bottomEncoderBPort: Int = 8
    }
  }
  class Climby {
    companion object {
      val hookPort: Int = 12
      val winchPort: Int = 5
    }
  }

  class Turret {
    companion object {
      // Motors
      val motorPort: Int = 4
    }
  }
}
