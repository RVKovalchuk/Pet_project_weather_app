package com.example.weatherapp.usecases

import android.util.Log
import com.example.weatherapp.classesFromJSONs.Hour
import com.example.weatherapp.classesFromJSONs.Weather

class GetListForHourFragmentUseCase {
    fun execute(weather: Weather): MutableList<Hour> {
        val listOfWeatherOnHour = mutableListOf<Hour>()

        for (i in 0 until weather.forecast.forecastday[0].hour.size) {
            listOfWeatherOnHour.add(weather.forecast.forecastday[0].hour[i])
        }

        Log.d("!!!!", "После добавления в список ЧАСОВ: ${listOfWeatherOnHour.size}")

        return listOfWeatherOnHour
    }

}