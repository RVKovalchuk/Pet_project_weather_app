package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "d16a0276d9604fa2ae7111416220107"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWeatherInfo("Силифке")
    }

    private fun getWeatherInfo(city : String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitInterface::class.java)
        service.getWeatherInfo(API_KEY, city).enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                Log.d("Log for weather", "${response.body()}")
                print(response.body())
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.d("Log for weather", "Failure!")
            }
        })
    }
}