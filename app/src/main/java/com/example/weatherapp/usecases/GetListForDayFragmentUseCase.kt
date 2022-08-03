package com.example.weatherapp.usecases

import android.util.Log
import com.example.weatherapp.classesFromJSONs.Forecastday
import com.example.weatherapp.classesFromJSONs.Weather

class GetListForDayFragmentUseCase {
    fun execute(weather: Weather): MutableList<Forecastday> {
        val listOfWeatherOnDay = mutableListOf<Forecastday>()

        for (i in 0 until weather.forecast.forecastday.size) {
            listOfWeatherOnDay.add(weather.forecast.forecastday[i])
        }

        Log.d("!!!!", "После добавления в список ДНЕЙ: ${listOfWeatherOnDay.size}")

        return listOfWeatherOnDay
    }
}