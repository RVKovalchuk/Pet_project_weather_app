package com.example.weatherapp

class WeatherModel(
    val city: String,
    val country: String,
    val time: String,
    val condition: String,
    val currentTemp: String,
    val maxTemperature: String,
    val minTemperature: String,
    val imageUrl: String,
    val infoPerHours: String
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
