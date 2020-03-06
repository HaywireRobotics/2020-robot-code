/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.cameraserver.CameraServer
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.PowerDistributionPanel
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import frc.robot.commands.*
import frc.robot.subsystems.*

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
class RobotContainer {
  val hyperdriveSubsystem: HyperdriveSubsystem = HyperdriveSubsystem()
  val m_exampleSubsystem: ExampleSubsystem = ExampleSubsystem()
  private val dockingBaySubsystem: DockingBaySubsystem = DockingBaySubsystem()
  private val turboLiftSubsystem: TurboLiftSubsystem = TurboLiftSubsystem()
  private val ionCannonySubsystem: IonCannony = IonCannony()
  private val colorSensorSubsystem: ColorSensorSubsystem = ColorSensorSubsystem()
  private val controlPanelSubsystem: ControlPanelSubsystem = ControlPanelSubsystem()
  private val climbySubsystem: ClimbySubsystem = ClimbySubsystem()
  
  private val turretSubsystem: TurretSubsystem = TurretSubsystem()

  private var autoCommandChooser: SendableChooser<Command> = SendableChooser()

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
    
    // Setting up the Autonomous Chooser
    autoCommandChooser.setDefaultOption("30% Speed, 1/2 Second Drive || Shooter Side",
                                          DriveForTime(hyperdriveSubsystem, 0.3, 0.5))
    autoCommandChooser.addOption("30% Speed, 1/2 Second Drive || Intake Side",
                                    DriveForTime(hyperdriveSubsystem, -0.3, 0.5))
    autoCommandChooser.addOption("Impulse Drive || Shooter Side",
                                    DriveForTime(hyperdriveSubsystem, 0.5, 0.1))
    autoCommandChooser.addOption("Impulse Drive || Intake Side",
                                    DriveForTime(hyperdriveSubsystem, -0.5, 0.1))
    
    autoCommandChooser.addOption("Turret, Shoot, 30% Speed, 1/2 Second Drive || Shooter Side",
        SequentialCommandGroup(TurretSeekAutonomous(turretSubsystem),
                              LaunchIonCannonForTimey(155000, 155000, 10, ionCannonySubsystem, turboLiftSubsystem),
                              DriveForTime(hyperdriveSubsystem, 0.3, 0.5)))
    autoCommandChooser.addOption("Turret, Shoot, 30% Speed, 1/2 Second Drive || Intake Side",
        SequentialCommandGroup(TurretSeekAutonomous(turretSubsystem),
                              LaunchIonCannonForTimey(155000, 155000, 10, ionCannonySubsystem, turboLiftSubsystem),
                              DriveForTime(hyperdriveSubsystem, -0.3, 0.5)))
    autoCommandChooser.addOption("Turret, Shoot, Impulse Drive || Shooter Side",
        SequentialCommandGroup(TurretSeekAutonomous(turretSubsystem),
                              LaunchIonCannonForTimey(155000, 155000, 10, ionCannonySubsystem, turboLiftSubsystem),
                              DriveForTime(hyperdriveSubsystem, 0.5, 0.1)))
    autoCommandChooser.addOption("Turret, Shoot, Impulse Drive || Intake Side",
        SequentialCommandGroup(TurretSeekAutonomous(turretSubsystem),
                              LaunchIonCannonForTimey(155000, 155000, 10, ionCannonySubsystem, turboLiftSubsystem),
                              DriveForTime(hyperdriveSubsystem, -0.5, 0.1)))
    
    autoCommandChooser.addOption("Turret, Shoot, Trench Run Pick Up",
        TrenchRunPickupAuto(ionCannonySubsystem,
                            turretSubsystem,
                            turboLiftSubsystem,
                            dockingBaySubsystem,
                            hyperdriveSubsystem))
    
    // Put that monstrosity on the dashboard
    SmartDashboard.putData("Auto mode", autoCommandChooser)
    
    hyperdriveSubsystem.defaultCommand = DriveHyperCommand(hyperdriveSubsystem, driverLeftJoystick, driverRightJoystick)
    // ionCannonySubsystem.setDefaultCommand(IonCannonyDefaultCommand(ionCannonySubsystem, manipulatorLeftJoystick, manipulatorLeftJoystick))
    turboLiftSubsystem.defaultCommand = TurboLiftyDefault(turboLiftSubsystem, manipulatorRightJoystick)
    turretSubsystem.defaultCommand = TurretManualDrive(turretSubsystem, manipulatorLeftJoystick)

    // Camera
//    var usbCamera = UsbCamera("cam0", 0)
//    usbCamera.setFPS(15)
//    usbCamera.setResolution(320, 240)
//    CameraServer.getInstance().startAutomaticCapture(usbCamera)
      CameraServer.getInstance().startAutomaticCapture()
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
    JoystickButton(driverLeftJoystick, 1).whileHeld(RunDockingBay(dockingBaySubsystem, PowerDistributionPanel(), -0.7))
    JoystickButton(driverLeftJoystick, 3).whileHeld(RunDockingBay(dockingBaySubsystem, PowerDistributionPanel(), 0.5))
    
    // Right Manipulator Button Bindings
    JoystickButton(manipulatorRightJoystick, 1).whileHeld(TestShooterNT(ionCannonySubsystem, turboLiftSubsystem))
    JoystickButton(manipulatorRightJoystick, 5).whileHeld(PrintColorSensorCommand(colorSensorSubsystem))
    JoystickButton(manipulatorRightJoystick, 7).whileHeld(RunColorMotor(controlPanelSubsystem))
    JoystickButton(manipulatorRightJoystick, 9).whileHeld(Winch(climbySubsystem))
    JoystickButton(manipulatorRightJoystick, 10).whileHeld(HookDown(climbySubsystem))
    JoystickButton(manipulatorRightJoystick, 11).whileHeld(HookUp(climbySubsystem))
    
    JoystickButton(manipulatorLeftJoystick, 10).whileHeld(TurretSeek(turretSubsystem))

    // Left Manipulator Button Bindings
    JoystickButton(manipulatorLeftJoystick, 1).whileHeld(LaunchIonCannon(32000.0, 32000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 2).whileHeld(LaunchIonCannon(64000.0, 64000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 3).whileHeld(LaunchIonCannon(96000.0, 96000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 4).whileHeld(LaunchIonCannon(128000.0, 128000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 5).whileHeld(LaunchIonCannon(160000.0, 160000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 6).whileHeld(LaunchIonCannon(192000.0, 192000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 7).whileHeld(LaunchIonCannon(224000.0, 224000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 8).whileHeld(LaunchIonCannon(256000.0, 256000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 9).whileHeld(LaunchIonCannon(288000.0, 288000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    //JoystickButton(manipulatorLeftJoystick, 10).whileHeld(LaunchIonCannon(320000.0, 320000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
    JoystickButton(manipulatorLeftJoystick, 11).whileHeld(LaunchIonCannon(80000.0, 100000.0, ionCannonySubsystem, turboLiftSubsystem, manipulatorLeftJoystick))
  }

  fun getAutonomousCommand(): Command {
    // Return the selected command
    return autoCommandChooser.selected
  }
}
