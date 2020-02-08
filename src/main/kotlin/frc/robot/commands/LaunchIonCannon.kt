/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.JSONPlotter
import frc.robot.subsystems.IonCannony
import frc.robot.subsystems.TurboLiftSubsystem

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.controller.PIDController

class LaunchIonCannon(val ionCannon: IonCannony, val turboLift: TurboLiftSubsystem, val topTargetRate: Number, val bottomTargetRate: Number) : CommandBase() {
  /**
   * Creates a new LaunchIonCannon.
   *
   * @param ionCannon The subsystem used by this command.
   */

  val topPIDController: PIDController
  val bottomPIDController: PIDController
  val topJSONPlotter: JSONPlotter = JSONPlotter()
  val bottomJSONPlotter: JSONPlotter = JSONPlotter()

  init {
    addRequirements(ionCannon, turboLift)

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
    turboLift.runSystem(0.4)
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    ionCannon.top.set(0.0)
    ionCannon.bottom.set(0.0)
    turboLift.runSystem(0.0)

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
    ionCannon.top.set(-output)
    println("TOP: " + output.toString())
    topJSONPlotter.recordPoint(ionCannon.topEncoderRate)
  }

  fun topGenerateMeasurement(): Double = ionCannon.topEncoderRate.toDouble()

  fun topGenerateSetpoint(): Double = topTargetRate.toDouble()

  fun bottomUseOutput(output: Double) {
    ionCannon.bottom.set(-output)
    println("BOTTOM: " + output.toString())
    bottomJSONPlotter.recordPoint(ionCannon.bottomEncoderRate)
  }

  fun bottomGenerateMeasurement(): Double = ionCannon.bottomEncoderRate

  fun bottomGenerateSetpoint(): Double = bottomTargetRate.toDouble()
}
