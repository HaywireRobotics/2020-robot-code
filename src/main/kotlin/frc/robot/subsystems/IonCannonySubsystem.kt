/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.CounterBase.EncodingType
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.PIDSourceType
import edu.wpi.first.wpilibj.controller.PIDController
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.Constants
import frc.robot.JSONPlotter
import frc.robot.JSONPlotterNT

class IonCannonySubsystem : SubsystemBase() {
	/**
	 * Creates a new IonCannony.
	 */
	val top: WPI_VictorSPX = WPI_VictorSPX(Constants.IonCannony.topPort)
	val bottom: WPI_VictorSPX = WPI_VictorSPX(Constants.IonCannony.bottomPort)

	// Encoders
	private val topEncoder: Encoder = Encoder(Constants.IonCannony.topEncoderAPort, Constants.IonCannony.topEncoderBPort, false, EncodingType.k4X)
	private val bottomEncoder: Encoder = Encoder(Constants.IonCannony.bottomEncoderAPort, Constants.IonCannony.bottomEncoderBPort, false, EncodingType.k4X)

	private var topEncoderRate: Double = 0.0
	private var bottomEncoderRate: Double = 0.0

	// PID Things
	private val topPIDController: PIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)
	private val bottomPIDController: PIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)
	private val topJSONPlotter: JSONPlotter = JSONPlotter("Ion Top")
	private val bottomJSONPlotter: JSONPlotter = JSONPlotter("Ion Bottom")
	private val topAverageJSONPlotter: JSONPlotter = JSONPlotter("Top Average")
	private val bottomAverageJSONPlotter: JSONPlotter = JSONPlotter("Bottom Average")
	private val jsonPlotterNT: JSONPlotterNT = JSONPlotterNT()

	var bottomSetpoint: Double = 0.0
	var topSetpoint: Double = 0.0

	// Launch when ready
	private val pointsUntilReady: Int = 15
	private val marginOfError: Double = 5000.0
	private var topLastData: MutableList<Double> = mutableListOf()
	private var bottomLastData: MutableList<Double> = mutableListOf()

	init {
		topEncoder.distancePerPulse = 1.0
		topEncoder.pidSourceType = PIDSourceType.kRate

		bottomEncoder.distancePerPulse = 1.0
		bottomEncoder.pidSourceType = PIDSourceType.kRate
	}

	/**
	 * Will be called periodically whenever the CommandScheduler runs.
	 */
	override fun periodic() {
		topEncoderRate = topEncoder.rate
		bottomEncoderRate = bottomEncoder.rate

		// Aggregating data
		topLastData = addDatapoint(topLastData, topEncoderRate)
		bottomLastData = addDatapoint(bottomLastData, bottomEncoderRate)

		// Plot averages

	}

	//* PID Functions
	fun resetPID() {
		topPIDController.reset()
		bottomPIDController.reset()

		topJSONPlotter.resetCapture()
		bottomJSONPlotter.resetCapture()
		topAverageJSONPlotter.resetCapture()
		bottomAverageJSONPlotter.resetCapture()

		topJSONPlotter.recordSetpoint(topSetpoint)
		bottomJSONPlotter.recordSetpoint(bottomSetpoint)
		topAverageJSONPlotter.recordSetpoint(topSetpoint)
		bottomAverageJSONPlotter.recordSetpoint(bottomSetpoint)
	}

	fun endPID() {
		top.set(0.0)
		bottom.set(0.0)

		jsonPlotterNT.publishJSONsToNT(listOf(topJSONPlotter.getJSON(), bottomJSONPlotter.getJSON(), topAverageJSONPlotter.getJSON(), bottomAverageJSONPlotter.getJSON()))

		println("TOP JSON")
		topJSONPlotter.outputDataAsJSON()

		println("BOTTOM JSON")
		bottomJSONPlotter.outputDataAsJSON()
	}

	fun setSetpoints(l_bottomSetpoint: Number, l_topSetpoint: Number) {
		bottomSetpoint = l_bottomSetpoint.toDouble()
		topSetpoint = l_topSetpoint.toDouble()
	}

	fun runPID() {
		topUseOutput(topPIDController.calculate(topEncoder.rate, topSetpoint))
		bottomUseOutput(bottomPIDController.calculate(bottomEncoder.rate, bottomSetpoint))
	}

	fun topUseOutput(output: Double) {
		top.set(-output)
		// println("TOP: " + output.toString())
		topJSONPlotter.recordPoint(topEncoderRate)
		topAverageJSONPlotter.recordPoint(topLastData.average())
	}

	fun bottomUseOutput(output: Double) {
		bottom.set(-output)
		// println("BOTTOM: " + output.toString())
		bottomJSONPlotter.recordPoint(bottomEncoderRate)
		topAverageJSONPlotter.recordPoint(bottomLastData.average())
	}
	//* End PID Functions

	//* Shoot when ready functions
	fun isReady(): Boolean {
		val topAverageValue = topLastData.average()
		val bottomAverageValue = bottomLastData.average()

		var topOK: Boolean = ((topAverageValue >= topSetpoint - marginOfError) && (topAverageValue <= topSetpoint + marginOfError))
		var bottomOK: Boolean = ((bottomAverageValue >= bottomSetpoint - marginOfError) && (bottomAverageValue <= bottomSetpoint + marginOfError))

		return (topOK && bottomOK)
	}

	private fun addDatapoint(list: MutableList<Double>, datapoint: Number): MutableList<Double> {
		list.add(datapoint.toDouble())

		if (list.size <= pointsUntilReady)
			return list

		list.removeAt(0)
		return list
	}
	//* End shoot when ready functions
}
