package com.harryawoodworth.multiweather.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherService {

    companion object {
        var BASE_URL = "https://api.weather.gov/gridpoints/"

        fun create(): WeatherService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(WeatherService::class.java)
        }
    }

    @GET("/forecast")
    fun getForecast() :
}