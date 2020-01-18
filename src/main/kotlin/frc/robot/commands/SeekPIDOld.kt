/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.subsystems.DrivetrainSubsystem
import frc.robot.commands.PIDCommandWrapper

import edu.wpi.first.networktables.EntryListenerFlags
import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.networktables.NetworkTableInstance
import frc.robot.Robot

import edu.wpi.first.wpilibj.controller.PIDController
// import edu.wpi.first.wpilibj2.command.PIDCommand

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
class SeekPIDOld(val driveTrainSubsystem: DrivetrainSubsystem) : PIDCommandWrapper(
            // The controller that the command will use
            PIDController(0.0, 0.0, 0.0),
            // This should return the measurement,
            { 0.0 },
            // This should return the setpoint (can also be a constant)
            { 0.0 },
            // This uses the output
            { output: Double -> 
              // Use the output here
              driveTrainSubsystem.tankDrive(output, -output)
            }) {

  val nt: NetworkTableInstance = NetworkTableInstance.getDefault()
  val table: NetworkTable
  val cameraTable: NetworkTable
  val entryx: NetworkTableEntry
  val entryy: NetworkTableEntry
  val cameraMode: NetworkTableEntry

  var cameraWidth: Int
  var cameraHeight: Int

  val deadZoneWidth: Int = 20 // The width of the deadZone
  init {
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling getController() here.
    addRequirements(driveTrainSubsystem)

    table = nt.getTable("datatable")
    entryx = table.getEntry("X")
    entryy = table.getEntry("Y")

    cameraTable = nt.getTable("CameraPublisher/rPi Camera 0")
    cameraMode = cameraTable.getEntry("mode")

    nt.startClientTeam(1569)

    cameraWidth = 360
    cameraHeight = 240

    // From initialize
    val cameraModeString = cameraMode.getString("")
    val cameraDimensions = Regex("(\\d+)x(\\d+)").findAll(cameraModeString).elementAt(0).groupValues

    cameraWidth = cameraDimensions[1].toInt()   // The width is at index 1
    cameraHeight = cameraDimensions[2].toInt()  // The height is at index 2
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }

  // Returns the setpoint
  fun generateSetpoint(): Double = cameraWidth.toDouble() / 2
}
