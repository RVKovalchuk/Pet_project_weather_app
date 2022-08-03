package com.example.weatherapp.usecases

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.WeatherModel

class FillMainCardUseCase {
    fun execute(weatherModel: WeatherModel, view: View, context: Context) {
        val city = view.findViewById<TextView>(R.id.fragment_main_textview_name_of_the_city)
        val currentTemperature =
            view.findViewById<TextView>(R.id.fragment_main_textview_current_temperature)
        val condition = view.findViewById<TextView>(R.id.fragment_main_textview_type_of_weather)
        val dateAndTime = view.findViewById<TextView>(R.id.fragment_main_textview_date_and_time)
        val imageOfWeather = view.findViewById<ImageView>(R.id.fragment_main_imageview_weather)
        val maxAndMinTemperature =
            view.findViewById<TextView>(R.id.fragment_main_max_and_min_temperature)

        city?.text = "${weatherModel.city}, ${weatherModel.country}"
        currentTemperature?.text = weatherModel.currentTemp + "°C"
        condition?.text = weatherModel.condition
        dateAndTime?.text = weatherModel.time
        maxAndMinTemperature?.text =
            weatherModel.maxTemperature + "°C / " + weatherModel.minTemperature + "°C"
        if (imageOfWeather != null) {
            Glide
                .with(context)
                .load("https:" + weatherModel.imageUrl)
                .centerCrop()
                .into(imageOfWeather)
        }
    }
}