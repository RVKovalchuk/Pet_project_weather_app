package com.example.weatherapp.classesFromJSONs

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)