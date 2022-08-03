package com.example.weatherapp.usecases

import com.example.weatherapp.WeatherModel
import com.example.weatherapp.classesFromJSONs.Weather

class ConvertFromWeatherToWeatherModelUseCase {
    fun execute(weather: Weather): WeatherModel {
        return WeatherModel(
            city = weather.location.name,
            country = weather.location.country,
            time = weather.location.localtime,
            condition = weather.current.condition.text,
            currentTemp = weather.current.temp_c.toString(),
            maxTemperature = "22",
            minTemperature = "12",
            imageUrl = weather.current.condition.icon,
            infoPerHours = "12112"
        )
    }
}