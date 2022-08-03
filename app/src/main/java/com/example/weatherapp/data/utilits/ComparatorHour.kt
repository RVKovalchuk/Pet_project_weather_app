package com.example.weatherapp.data.utilits

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.classesFromJSONs.Forecastday
import com.example.weatherapp.classesFromJSONs.Hour

class ComparatorHour : DiffUtil.ItemCallback<Hour>() {
    override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
        return oldItem == newItem
    }
}