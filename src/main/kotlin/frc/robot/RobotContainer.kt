/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import frc.robot.commands.ExampleCommand
import frc.robot.commands.SeekPID
import frc.robot.commands.DriveHyperCommand
import frc.robot.commands.IonCannonyDefaultCommand
import frc.robot.commands.TurboLiftyDefault
import frc.robot.commands.RunDockingBay
import frc.robot.commands.PIDShoot
import frc.robot.commands.PrintColorSensorCommand
import frc.robot.commands.runColorMotor
import frc.robot.subsystems.*

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj2.command.button.JoystickButton


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private val hyperdriveSubsystem: HyperdriveSubsystem = HyperdriveSubsystem()
  private val m_exampleSubsystem: ExampleSubsystem = ExampleSubsystem()
  private val dockingBaySubsystem: DockingBaySubsystem = DockingBaySubsystem()
  private val turboLiftSubsystem: TurboLiftSubsystem = TurboLiftSubsystem()
  private val ionCannonySubsystem: IonCannony = IonCannony()
  private val colorSensorSubsystem: ColorSensorSubsystem = ColorSensorSubsystem()
  private val controlPanelSubsystem: ControlPanelSubsystem = ControlPanelSubsystem()
  

  val m_autoCommand: ExampleCommand = ExampleCommand(m_exampleSubsystem)

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
    // ionCannonySubsystem.setDefaultCommand(IonCannonyDefaultCommand(ionCannonySubsystem, driverRightJoystick, driverLeftJoystick))
    turboLiftSubsystem.setDefaultCommand(TurboLiftyDefault(turboLiftSubsystem, manipulatorRightJoystick))

    //Add a color sensor on the I2C port
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  fun configureButtonBindings() {
    val manipRightJoyBut1: JoystickButton = JoystickButton(manipulatorRightJoystick, 1)
    val manipRightJoyBut2: JoystickButton = JoystickButton(manipulatorRightJoystick, 2)
    val manipRightJoyBut3: JoystickButton = JoystickButton(manipulatorRightJoystick, 3)
    val manipRightJoyBut5: JoystickButton = JoystickButton(manipulatorRightJoystick, 5)
    val manipRightJoyBut7: JoystickButton = JoystickButton(manipulatorRightJoystick, 7)

    val manipLeftJoyBut1: JoystickButton = JoystickButton(manipulatorLeftJoystick, 1)
    val manipLeftJoyBut2: JoystickButton = JoystickButton(manipulatorLeftJoystick, 2)
    val manipLeftJoyBut3: JoystickButton = JoystickButton(manipulatorLeftJoystick, 3)
    val manipLeftJoyBut4: JoystickButton = JoystickButton(manipulatorLeftJoystick, 4)
    val manipLeftJoyBut5: JoystickButton = JoystickButton(manipulatorLeftJoystick, 5)
    val manipLeftJoyBut6: JoystickButton = JoystickButton(manipulatorLeftJoystick, 6)
    val manipLeftJoyBut7: JoystickButton = JoystickButton(manipulatorLeftJoystick, 7)
    val manipLeftJoyBut8: JoystickButton = JoystickButton(manipulatorLeftJoystick, 8)
    val manipLeftJoyBut9: JoystickButton = JoystickButton(manipulatorLeftJoystick, 9)
    val manipLeftJoyBut10: JoystickButton = JoystickButton(manipulatorLeftJoystick, 10)
    val manipLeftJoyBut11: JoystickButton = JoystickButton(manipulatorLeftJoystick, 11)

    val driverLeftJoyBut1: JoystickButton = JoystickButton(driverLeftJoystick, 1)

    driverLeftJoyBut1.whileHeld(RunDockingBay(dockingBaySubsystem))
    // manipRightJoyBut3.whileHeld()
    manipRightJoyBut5.whileHeld(PrintColorSensorCommand(colorSensorSubsystem))
    manipRightJoyBut7.whileHeld(runColorMotor(controlPanelSubsystem))

  
    manipLeftJoyBut1.whileHeld(PIDShoot(-32000.0, 32000.0, ionCannonySubsystem))
    manipLeftJoyBut2.whileHeld(PIDShoot(-64000.0, 64000.0, ionCannonySubsystem))
    manipLeftJoyBut3.whileHeld(PIDShoot(-96000.0, 96000.0, ionCannonySubsystem))
    manipLeftJoyBut4.whileHeld(PIDShoot(-128000.0, 128000.0, ionCannonySubsystem))
    manipLeftJoyBut5.whileHeld(PIDShoot(-160000.0, 160000.0, ionCannonySubsystem))
    manipLeftJoyBut6.whileHeld(PIDShoot(-192000.0, 192000.0, ionCannonySubsystem))
    manipLeftJoyBut7.whileHeld(PIDShoot(-224000.0, 224000.0, ionCannonySubsystem))
    manipLeftJoyBut8.whileHeld(PIDShoot(-256000.0, 256000.0, ionCannonySubsystem))
    manipLeftJoyBut9.whileHeld(PIDShoot(-288000.0, 288000.0, ionCannonySubsystem))
    manipLeftJoyBut10.whileHeld(PIDShoot(-320000.0, 320000.0, ionCannonySubsystem))
    manipLeftJoyBut11.whileHeld(PIDShoot(-80000.0, 100000.0, ionCannonySubsystem))
  }

  fun getAutonomousCommand(): Command {
    // Return the selected command
    return m_autoCommandChooser.getSelected()
  }
}
