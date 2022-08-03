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
import com.example.weatherapp.classesFromJSONs.Hour
import com.example.weatherapp.data.utilits.ComparatorForecast
import com.example.weatherapp.data.utilits.ComparatorHour

class RecyclerViewAdapterHours :
    ListAdapter<Hour, RecyclerViewAdapterHours.ViewHolder>(ComparatorHour()) {
    var listForHourFragment = mutableListOf<Hour>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Hour) {
            val textDate = itemView.findViewById<TextView>(R.id.list_item_weather_date)
            val textWeatherType = itemView.findViewById<TextView>(R.id.list_item_weather_type)
            val textTemperature =
                itemView.findViewById<TextView>(R.id.list_item_weather_temperature)
            val imageWeatherType = itemView.findViewById<ImageView>(R.id.list_item_weather_image)

            textDate.text = item.time
            textWeatherType.text = item.condition.text
            textTemperature.text = item.temp_c.toString() + "°C"

            Glide
                .with(itemView.context)
                .load("http:" + item.condition.icon)
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
