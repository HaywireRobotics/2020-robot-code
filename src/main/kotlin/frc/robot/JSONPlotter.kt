/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.networktables.NetworkTableInstance

class JSONPlotter(private var label: String) {

	private val pointsList: MutableList<Double> = mutableListOf()
	private var setpoint: Double = 0.0

	private val moshi: Moshi = Moshi.Builder().build()
	private val adapter: JsonAdapter<JSONModel> = moshi.adapter(JSONModel::class.java)

	fun resetCapture() {
		// Empty list
		pointsList.clear()
	}

	fun recordSetpoint(point: Number) {
		setpoint = point.toDouble()
	}

	fun recordPoint(point: Number) {
		// Add point to list
		pointsList.add(point.toDouble())
	}

	fun changeLabel(newLabel: String) {
		label = newLabel
	}

	fun outputDataAsJSON() {
		// Output json formatted data to console
		println(adapter.toJson(JSONModel(label, setpoint, pointsList)))
	}

	fun getJSON() = adapter.toJson(JSONModel(label, setpoint, pointsList))
}

class JSONPlotterNT {
	private val ntInst: NetworkTableInstance = NetworkTableInstance.getDefault()
	private val table: NetworkTable = ntInst.getTable("/")
	private val jsonStringEntry: NetworkTableEntry = table.getEntry("json_string")

	init {
		jsonStringEntry.setString("{\"label\": \"default\", \"setpoint\": 0.0, \"data\": []}")
	}

	fun publishJSONsToNT(json: List<String>) {
		jsonStringEntry.setString(json.joinToString(separator = "|"))
	}
}

@JsonClass(generateAdapter = true)
data class JSONModel(
		val label: String,
		val setpoint: Double,
		val data: MutableList<Double>
)
