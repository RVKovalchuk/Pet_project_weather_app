package com.example.weatherapp.data.utilits

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.classesFromJSONs.Forecastday

class ComparatorForecast : DiffUtil.ItemCallback<Forecastday>() {
    override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
        return oldItem == newItem
    }
}