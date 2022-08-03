package com.example.weatherapp.data.api

import com.example.weatherapp.classesFromJSONs.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET(RetrofitConstants.QUERY_URL)
    fun getWeather(@Query("key") key: String,
                   @Query("q") city: String,
                   @Query("days") days : String
    ): Call<Weather>
}