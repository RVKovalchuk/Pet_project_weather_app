package com.example.weatherapp.classesFromJSONs

data class Hour(
    val condition: Condition,
    val temp_c: Double,
    val time: String
)