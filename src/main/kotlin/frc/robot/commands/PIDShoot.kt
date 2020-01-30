/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.JSONPlotter
import frc.robot.subsystems.IonCannony

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.controller.PIDController

class PIDShoot(val bottomTargetRate: Double, val topTargetRate: Double, val m_subsystem: IonCannony) : CommandBase() {
  /**
   * Creates a new PIDShoot.
   *
   * @param m_subsystem The subsystem used by this command.
   */

  val topPIDController: PIDController
  val bottomPIDController: PIDController
  val topJSONPlotter: JSONPlotter = JSONPlotter()
  val bottomJSONPlotter: JSONPlotter = JSONPlotter()

  init {
    addRequirements(m_subsystem)

    topPIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)
    bottomPIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
    topPIDController.reset()
    bottomPIDController.reset()
    topJSONPlotter.resetCapture()
    bottomJSONPlotter.resetCapture()
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    topUseOutput(topPIDController.calculate(topGenerateMeasurement(), topGenerateSetpoint()))
    bottomUseOutput(bottomPIDController.calculate(bottomGenerateMeasurement(), bottomGenerateSetpoint()))
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    m_subsystem.top.set(0.0)
    m_subsystem.bottom.set(0.0)

    println("TOP JSON")
    topJSONPlotter.outputDataAsJSON()
    
    println("BOTTOM JSON")
    bottomJSONPlotter.outputDataAsJSON()
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }

  fun topUseOutput(output: Double) {
    //m_subsystem.set(-maxOf(output, 0.005)-0.1)
    // m_subsystem.top.set(-output - 0.5)
    m_subsystem.top.set(-output)
    println("TOP: " + output.toString())
    topJSONPlotter.recordPoint(m_subsystem.topEncoderRate)
  }

  fun topGenerateMeasurement(): Double = m_subsystem.topEncoderRate

  fun topGenerateSetpoint(): Double = topTargetRate

  fun bottomUseOutput(output: Double) {
    //m_subsystem.set(-maxOf(output, 0.005)-0.1)
    // m_subsystem.bottom.set(-output - 0.5)
    m_subsystem.bottom.set(-output)
    println("BOTTOM: " + output.toString())
    bottomJSONPlotter.recordPoint(m_subsystem.bottomEncoderRate)
  }

  fun bottomGenerateMeasurement(): Double = m_subsystem.bottomEncoderRate

  fun bottomGenerateSetpoint(): Double = bottomTargetRate
}
