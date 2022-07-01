package com.example.weatherapp

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("v1/current.json?aqi=no")
    fun getWeatherInfo(@Query("key") key: String, @Query("q") city: String):
            retrofit2.Call<Weather>
}