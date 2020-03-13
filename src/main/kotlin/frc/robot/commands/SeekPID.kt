/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj.controller.PIDController
import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.HyperdriveSubsystem

class SeekPID(private val hyperdriveSubsystem: HyperdriveSubsystem) : CommandBase() {
	/**
	 * Creates a new SeekPID.
	 *
	 * @param hyperdriveSubsystem The subsystem used by this command.
	 */

	private val pidController: PIDController

	private val nt: NetworkTableInstance = NetworkTableInstance.getDefault()
	private val table: NetworkTable
	private val cameraTable: NetworkTable
	private val entryX: NetworkTableEntry
	private val entryY: NetworkTableEntry
	private val cameraMode: NetworkTableEntry

	private var cameraWidth: Int
	private var cameraHeight: Int

	val deadZoneWidth: Int = 20 // The width of the deadZone

	init {
		addRequirements(hyperdriveSubsystem)

		pidController = PIDController(-0.2, 100.0, 10000.0)

		table = nt.getTable("datatable")
		entryX = table.getEntry("X")
		entryY = table.getEntry("Y")

		cameraTable = nt.getTable("CameraPublisher/rPi Camera 0")
		cameraMode = cameraTable.getEntry("mode")

		nt.startClientTeam(1569)

		cameraWidth = 360
		cameraHeight = 240
	}

	// Called when the command is initially scheduled.
	override fun initialize() {
		pidController.reset()

		val cameraModeString = cameraMode.getString("")
		val cameraDimensions = Regex("(\\d+)x(\\d+)").findAll(cameraModeString).elementAt(0).groupValues

		cameraWidth = cameraDimensions[1].toInt()   // The width is at index 1
		cameraHeight = cameraDimensions[2].toInt()  // The height is at index 2
	}

	// Called every time the scheduler runs while the command is scheduled.
	override fun execute() {
		useOutput(pidController.calculate(generateMeasurement(), generateSetpoint()))
	}

	// Called once the command ends or is interrupted.
	override fun end(interrupted: Boolean) {
		useOutput(0.0)
	}

	// Returns true when the command should end.
	override fun isFinished(): Boolean {
		return false
	}

	fun useOutput(output: Double) = hyperdriveSubsystem.tankDrive(output, -output)

	fun generateMeasurement(): Double {
		return entryX.getDouble(cameraWidth.toDouble() / 2)
	}

	fun generateSetpoint(): Double {
		return cameraWidth.toDouble() / 2
	}
}
