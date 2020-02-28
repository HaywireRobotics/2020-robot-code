/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import frc.robot.JSONPlotter
import frc.robot.JSONPlotterNT
import edu.wpi.first.wpilibj.controller.PIDController

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.CounterBase.EncodingType
import edu.wpi.first.wpilibj.PIDSourceType

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX

import frc.robot.Constants

class IonCannony : SubsystemBase() {
  /**
   * Creates a new IonCannony.
   */
  val top: WPI_VictorSPX = WPI_VictorSPX(Constants.IonCannony.topPort)
  val bottom: WPI_VictorSPX = WPI_VictorSPX(Constants.IonCannony.bottomPort)

  // Encoders
  private val topEncoder: Encoder = Encoder(Constants.IonCannony.topEncoderAPort, Constants.IonCannony.topEncoderBPort, false, EncodingType.k4X)
  private val bottomEncoder: Encoder = Encoder(Constants.IonCannony.bottomEncoderAPort, Constants.IonCannony.bottomEncoderBPort, false, EncodingType.k4X)

  var topEncoderRate: Double = 0.0
  var bottomEncoderRate: Double = 0.0

  // PID Things
  val topPIDController: PIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)
  // val topPIDController: PIDController = PIDController(0.0197, 0.0, 0.0)
  val bottomPIDController: PIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)
  // val bottomPIDController: PIDController = PIDController(0.0188, 0.0, 0.0)
  val topJSONPlotter: JSONPlotter = JSONPlotter("Ion Top")
  val bottomJSONPlotter: JSONPlotter = JSONPlotter("Ion Bottom")
  val jsonPlotterNT: JSONPlotterNT = JSONPlotterNT()

  var bottomSetpoint: Double = 0.0
  var topSetpoint: Double = 0.0

  init {
    topEncoder.setDistancePerPulse(1.0)
    topEncoder.setPIDSourceType(PIDSourceType.kRate)

    bottomEncoder.setDistancePerPulse(1.0)
    bottomEncoder.setPIDSourceType(PIDSourceType.kRate)
  }

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
    topEncoderRate = topEncoder.getRate()
    bottomEncoderRate = bottomEncoder.getRate()
    // println("TOP E: " + topEncoderRate)
    // println("BOTTOM E: " + bottomEncoderRate)
  }

  fun resetPID() {
    topPIDController.reset()
    bottomPIDController.reset()

    topJSONPlotter.resetCapture()
    bottomJSONPlotter.resetCapture()
    
    topJSONPlotter.recordSetpoint(topSetpoint)
    bottomJSONPlotter.recordSetpoint(bottomSetpoint)
  }

  fun endPID() {
    top.set(0.0)
    bottom.set(0.0)

    jsonPlotterNT.publishJSONsToNT(listOf(topJSONPlotter.getJSON(), bottomJSONPlotter.getJSON()))

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
    topUseOutput(topPIDController.calculate(topEncoder.getRate(), topSetpoint))
    bottomUseOutput(bottomPIDController.calculate(bottomEncoder.getRate(), bottomSetpoint))
  }

  fun topUseOutput(output: Double) {
    top.set(-output)
    // println("TOP: " + output.toString())
    topJSONPlotter.recordPoint(topEncoderRate)
  }

  fun bottomUseOutput(output: Double) {
    bottom.set(-output)
    // println("BOTTOM: " + output.toString())
    bottomJSONPlotter.recordPoint(bottomEncoderRate)
  }
}
