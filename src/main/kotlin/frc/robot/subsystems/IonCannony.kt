/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

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
  private val topEncoder: Encoder = Encoder(Constants.IonCannony.topEncoderAPort, Constants.IonCannony.topEncoderBPort, true, EncodingType.k4X)
  private val bottomEncoder: Encoder = Encoder(Constants.IonCannony.bottomEncoderAPort, Constants.IonCannony.bottomEncoderBPort, true, EncodingType.k4X)

  var topEncoderRate: Double = 0.0
  var bottomEncoderRate: Double = 0.0

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
  }
}
