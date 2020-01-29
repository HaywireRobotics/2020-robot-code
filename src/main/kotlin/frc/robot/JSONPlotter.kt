/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.wpilibj2.command.SubsystemBase

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.JsonAdapter

class JSONPlotter {
  
  private val pointsList: MutableList<Double> = mutableListOf()
  
  val moshi: Moshi = Moshi.Builder().build()
  val adapter: JsonAdapter<JSONModel> = moshi.adapter(JSONModel::class.java)

  fun resetCapture() {
    // Empty list
    pointsList.clear()
  }

  fun recordPoint(point: Number) {
    // Add point to list
    pointsList.add(point.toDouble())
  }

  fun outputDataAsJSON() {
    // Output json formatted data to console
    println(adapter.toJson(JSONModel(pointsList)))
  }
}

@JsonClass(generateAdapter = true)
data class JSONModel (
    val data: MutableList<Double>
)
