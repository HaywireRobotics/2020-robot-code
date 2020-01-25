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
    
    // hyperdriveSubsystem.setDefaultCommand(DriveHyperCommand(hyperdriveSubsystem, driverLeftJoystick, driverRightJoystick))
    ionCannonySubsystem.setDefaultCommand(IonCannonyDefaultCommand(ionCannonySubsystem, driverRightJoystick, driverLeftJoystick))
    turboLiftSubsystem.setDefaultCommand(TurboLiftyDefault(turboLiftSubsystem, manipulatorRightJoystick, manipulatorLeftJoystick))
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  fun configureButtonBindings() {
    val manipJoyBut1: JoystickButton = JoystickButton(manipulatorRightJoystick, 1)
    val manipJoyBut3: JoystickButton = JoystickButton(manipulatorRightJoystick, 3)

    manipJoyBut1.whileHeld(RunDockingBay(dockingBaySubsystem))
    manipJoyBut3.whileHeld(PIDShoot(200000.0, 200000.0, ionCannonySubsystem))
  }

  fun getAutonomousCommand(): Command {
    // Return the selected command
    return m_autoCommandChooser.getSelected()
  }
}
