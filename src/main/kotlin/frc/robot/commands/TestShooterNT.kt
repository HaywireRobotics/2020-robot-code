/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands

import frc.robot.JSONPlotter
import frc.robot.JSONPlotterNT
import frc.robot.subsystems.IonCannony

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj.controller.PIDController

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.networktables.EntryListenerFlags

class TestShooterNT(val m_subsystem: IonCannony) : CommandBase() {
  /**
   * Creates a new TestShooterNT.
   *
   * @param m_subsystem The subsystem used by this command.
   */

  val topPIDController: PIDController
  val bottomPIDController: PIDController
  val topJSONPlotter: JSONPlotter = JSONPlotter("Ion Top")
  val bottomJSONPlotter: JSONPlotter = JSONPlotter("Ion Bottom")
  val jsonPlotterNT: JSONPlotterNT = JSONPlotterNT()

  val nt: NetworkTableInstance = NetworkTableInstance.getDefault()
  val table: NetworkTable
  val topShooterSpeedEntry: NetworkTableEntry
  val bottomShooterSpeedEntry: NetworkTableEntry

  var topTargetRate: Double = 0.0
  var bottomTargetRate: Double = 0.0

  init {
    addRequirements(m_subsystem)

    topPIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)
    bottomPIDController = PIDController(0.0000125 * 0.45, 0.0000125 * 0.94, 0.000000175)

    table = nt.getTable("datatable")
    topShooterSpeedEntry = table.getEntry("topShooterSpeed")
    bottomShooterSpeedEntry = table.getEntry("bottomShooterSpeed")

    nt.startClientTeam(1569)
  }

  // Called when the command is initially scheduled.
  override fun initialize() {
    topTargetRate = topShooterSpeedEntry.getDouble(0.0)
    bottomTargetRate = bottomShooterSpeedEntry.getDouble(0.0)

    println("TOP Setpoint: " + topTargetRate)
    println("BOTTOM Setpoint: " + bottomTargetRate)
    m_subsystem.setSetpoints(bottomTargetRate, topTargetRate)
    m_subsystem.resetPID()
  }

  // Called every time the scheduler runs while the command is scheduled.
  override fun execute() {
    m_subsystem.runPID()
  }

  // Called once the command ends or is interrupted.
  override fun end(interrupted: Boolean) {
    m_subsystem.endPID()
  }

  // Returns true when the command should end.
  override fun isFinished(): Boolean {
    return false
  }
}
