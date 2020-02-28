/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import frc.robot.commands.*
import frc.robot.subsystems.*

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.PowerDistributionPanel
import edu.wpi.first.wpilibj2.command.button.JoystickButton

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
class RobotContainer {
  val hyperdriveSubsystem: HyperdriveSubsystem = HyperdriveSubsystem()
  val m_exampleSubsystem: ExampleSubsystem = ExampleSubsystem()
  val dockingBaySubsystem: DockingBaySubsystem = DockingBaySubsystem()
  val turboLiftSubsystem: TurboLiftSubsystem = TurboLiftSubsystem()
  val ionCannonySubsystem: IonCannony = IonCannony()
  val colorSensorSubsystem: ColorSensorSubsystem = ColorSensorSubsystem()
  val controlPanelSubsystem: ControlPanelSubsystem = ControlPanelSubsystem()
  val climbySubsystem: ClimbySubsystem = ClimbySubsystem()
  
  val turretSubsystem: TurretSubsystem = TurretSubsystem()

  val m_autoCommand: DriveForTime = DriveForTime(hyperdriveSubsystem, 0.5, 0.5)

  var m_autoCommandChooser: SendableChooser<Command> = SendableChooser()

  // Joysticks
  private val manipulatorRightJoystick: Joystick = Joystick(Constants.Joysticks.manipulatorRightPort)
  private val manipulatorLeftJoystick: Joystick = Joystick(Constants.Joysticks.manipulatorLeftPort)
  private val driverLeftJoystick: Joystick = Joystick(Constants.Joysticks.driverLeftPort)
  private val driverRightJoystick: Joystick = Joystick(Constants.Joysticks.driverRightPort)

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  init {
    // Configure the button bindings
    configureButtonBindings()
    m_autoCommandChooser.setDefaultOption("Default Auto", m_autoCommand)
    SmartDashboard.putData("Auto mode", m_autoCommandChooser)
    
    hyperdriveSubsystem.setDefaultCommand(DriveHyperCommand(hyperdriveSubsystem, driverLeftJoystick, driverRightJoystick))
    // ionCannonySubsystem.setDefaultCommand(IonCannonyDefaultCommand(ionCannonySubsystem, manipulatorLeftJoystick, manipulatorLeftJoystick))
    turboLiftSubsystem.setDefaultCommand(TurboLiftyDefault(turboLiftSubsystem, manipulatorRightJoystick))
    turretSubsystem.setDefaultCommand(TurretManualDrive(turretSubsystem, manipulatorLeftJoystick))

    //Add a color sensor on the I2C port
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  fun configureButtonBindings() {
    // Right Driver Button Bindings
    JoystickButton(driverRightJoystick, 1).whenPressed(SwitchDriveDirection(hyperdriveSubsystem))

    // Left Driver Button Bindings
    JoystickButton(driverLeftJoystick, 1).whileHeld(RunDockingBay(dockingBaySubsystem, PowerDistributionPanel()))
    
    // Right Manipulator Button Bindings
    JoystickButton(manipulatorRightJoystick, 1).whileHeld(TestShooterNT(ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorRightJoystick, 5).whileHeld(PrintColorSensorCommand(colorSensorSubsystem))
    JoystickButton(manipulatorRightJoystick, 7).whileHeld(RunColorMotor(controlPanelSubsystem))
    JoystickButton(manipulatorRightJoystick, 9).whileHeld(Winch(climbySubsystem))
    JoystickButton(manipulatorRightJoystick, 10).whileHeld(HookUp(climbySubsystem))
    JoystickButton(manipulatorRightJoystick, 11).whileHeld(HookDown(climbySubsystem))
    
    JoystickButton(manipulatorLeftJoystick, 10).whileHeld(TurretSeek(turretSubsystem))

    // Left Manipulator Button Bindings
    JoystickButton(manipulatorLeftJoystick, 1).whileHeld(LaunchIonCannon(32000.0, 32000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 2).whileHeld(LaunchIonCannon(64000.0, 64000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 3).whileHeld(LaunchIonCannon(96000.0, 96000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 4).whileHeld(LaunchIonCannon(128000.0, 128000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 5).whileHeld(LaunchIonCannon(160000.0, 160000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 6).whileHeld(LaunchIonCannon(192000.0, 192000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 7).whileHeld(LaunchIonCannon(224000.0, 224000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 8).whileHeld(LaunchIonCannon(256000.0, 256000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 9).whileHeld(LaunchIonCannon(288000.0, 288000.0, ionCannonySubsystem, turboLiftSubsystem))
    //JoystickButton(manipulatorLeftJoystick, 10).whileHeld(LaunchIonCannon(320000.0, 320000.0, ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorLeftJoystick, 11).whileHeld(LaunchIonCannon(80000.0, 100000.0, ionCannonySubsystem, turboLiftSubsystem))
  }

  fun getAutonomousCommand(): Command {
    // Return the selected command
    return m_autoCommandChooser.getSelected()
  }
}
