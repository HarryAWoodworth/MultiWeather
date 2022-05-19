package com.harryawoodworth.multiweather.data.api

import com.harryawoodworth.multiweather.data.model.WeatherResponseModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface for making calls to the Weather API Endpoint
 */
interface WeatherService {

    /**
     * Returns created WeatherService from Retrofit built with the Weather API endpoint
     * Uses a Gson converter factory to convert the JSON response object to a Java object
     */
    companion object {

        var BASE_URL = "https://api.weather.gov/gridpoints/"

        fun create(): WeatherService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(WeatherService::class.java)
        }
    }

    /**
     * Get the weather forecast for an area determined by the station and grid point
     * Suspending functions can suspend the execution of a coroutine: https://kotlinlang.org/docs/coroutines-basics.html#extract-function-refactoring
     */
    @GET("/gridpoints/{station}/{gridX},{gridY}/forecast")
    suspend fun getForecast(
        @Path("station") station: String,
        @Path("gridX") gridX: Int,
        @Path("gridY") gridY: Int
    ) : Response<WeatherResponseModel>?

    /**
     * Get the gridpoint
     */
    @GET("/points/{latitude},{longitude}")
    suspend fun getGridpointAPIEndpoint(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): Response<WeatherResponseModel>?
}