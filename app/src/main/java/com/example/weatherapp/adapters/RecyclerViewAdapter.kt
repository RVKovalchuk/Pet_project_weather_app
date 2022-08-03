package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.classesFromJSONs.Forecastday
import com.example.weatherapp.classesFromJSONs.Hour
import com.example.weatherapp.data.utilits.ComparatorForecast

class RecyclerViewAdapter :
    ListAdapter<Forecastday, RecyclerViewAdapter.ViewHolder>(ComparatorForecast()) {
    var listForDayFragment = mutableListOf<Forecastday>()
    var listForHourFragment = mutableListOf<Hour>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Forecastday) {
            val textDate = itemView.findViewById<TextView>(R.id.list_item_weather_date)
            val textWeatherType = itemView.findViewById<TextView>(R.id.list_item_weather_type)
            val textTemperature =
                itemView.findViewById<TextView>(R.id.list_item_weather_temperature)
            val imageWeatherType = itemView.findViewById<ImageView>(R.id.list_item_weather_image)

            textDate.text = item.date
            textWeatherType.text = item.day.condition.text
            textTemperature.text =
                item.day.maxtemp_c.toString() + "°C /" + item.day.mintemp_c.toString() + "°C"

            Glide
                .with(itemView.context)
                .load("http:" + item.day.condition.icon)
                .into(imageWeatherType)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_weather, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}
