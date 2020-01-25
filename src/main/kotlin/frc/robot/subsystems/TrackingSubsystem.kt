/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.*

class TrackingSubsystem : SubsystemBase() {
  /**
   * Creates a new TrackingSubsystem.
   */
  private val ntInst: NetworkTableInstance
  
  private val table: NetworkTable
    private val exposureEntry: NetworkTableEntry
    private val xEntry: NetworkTableEntry
    private val yEntry: NetworkTableEntry
    private val distanceEntry: NetworkTableEntry

  init {
    ntInst =  NetworkTableInstance.getDefault()
    ntInst.startClientTeam(1569)
    table = ntInst.getTable("datatable")
    exposureEntry = table.getEntry("setExposure")
    xEntry = table.getEntry("X")
    yEntry = table.getEntry("Y")
    distanceEntry = table.getEntry("Distance")
  }

  /**
   * Will be called periodically whenever the CommandScheduler runs.
   */
  override fun periodic() {
  }

fun setExposure(exposure: Double) = exposureEntry.setDouble(exposure)
fun startCapture(){
  setExposure(2.0)
}
fun endCapture(){
  setExposure(10.0)
}
fun getTarget():Triple<Number,Number,Number>{
  return Triple(xEntry.getNumber(-1.0),yEntry.getNumber(-1.0),distanceEntry.getNumber(-1.0))
}
}
