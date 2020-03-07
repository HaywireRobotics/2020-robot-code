/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import com.revrobotics.ColorSensorV3
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.util.Color
import edu.wpi.first.wpilibj2.command.SubsystemBase

class ColorSensorSubsystem : SubsystemBase() {
	/**
	 * Creates a new ColorSensorSubsystem.
	 */
	//color sensor stuff
	var m_colorSensor: ColorSensorV3

	init {
		val i2cPort = I2C.Port.kOnboard
		m_colorSensor = ColorSensorV3(i2cPort)
	}

	/**
	 * Will be called periodically whenever the CommandScheduler runs.
	 */
	override fun periodic() {
	}

	fun getColorRGB(): Color {
		return m_colorSensor.color
	}

	fun getRawIR(): Number {
		return m_colorSensor.ir
	}


}
