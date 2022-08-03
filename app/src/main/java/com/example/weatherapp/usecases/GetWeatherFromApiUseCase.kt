package com.example.weatherapp.usecases

import android.content.Context
import android.util.Log
import android.view.View
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.classesFromJSONs.*
import com.example.weatherapp.data.api.RetrofitConstants
import com.example.weatherapp.data.api.RetrofitInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class GetWeatherFromApiUseCase(
    val convertFromWeatherToWeatherModel: ConvertFromWeatherToWeatherModelUseCase,
    val fillMainCard: FillMainCardUseCase,
    val getListForDayFragmentUseCase: GetListForDayFragmentUseCase,
    val getListForHourFragmentUseCase: GetListForHourFragmentUseCase,
    val view: View, val context: Context
) {

    fun execute(
        city: String,
        doOnResponse: (list: MutableList<Forecastday>) -> Unit,
        doOnResponseHour: (list: MutableList<Hour>) -> Unit
    ) {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(RetrofitInterface::class.java)
        service.getWeather(key = RetrofitConstants.RETROFIT_KEY, city = city, days = "3")
            .enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    Log.d("!!!!", "Везер с АПИ: ${response.body()}")

                    val weatherModel = response.body()
                        ?.let { convertFromWeatherToWeatherModel.execute(it) }

                    if (weatherModel != null) {
                        fillMainCard.execute(
                            weatherModel = weatherModel,
                            view = view,
                            context = context,
                        )
                    }

                    val listForDaysFragment =
                        response.body()?.let { getListForDayFragmentUseCase.execute(it) }!!
                    doOnResponse.invoke(listForDaysFragment)
                    Log.d("!!!!", "Список для DAYS: ${listForDaysFragment.size}")

                    val listForHourFragment =
                        response.body()?.let { getListForHourFragmentUseCase.execute(it) }!!
                    doOnResponseHour.invoke(listForHourFragment)
                }


                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }
}