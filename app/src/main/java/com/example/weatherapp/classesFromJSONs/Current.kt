package com.example.weatherapp.classesFromJSONs

data class Current(
    val condition: Condition,
    val is_day: Int,
    val temp_c: Double,
    val uv: Double
)