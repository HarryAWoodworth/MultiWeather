package com.harryawoodworth.multiweather.data.model

import com.google.gson.annotations.SerializedName

/**
 * Model for the data contained in the weather forecast from the API
 * @SerializedName is used to determine the exact key in the response, and avoid obfuscation issues
 * https://stackoverflow.com/questions/28957285/what-is-the-basic-purpose-of-serializedname-annotation-in-android-using-gson
 */
data class ForecastModel(
    @SerializedName("isDaytime")
    val isDaytime: Boolean = true,
    @SerializedName("temperature")
    val temperature: Int = 0,
    @SerializedName("windSpeed")
    val windSpeed: String = "0 mph",
    @SerializedName("shortForecast")
    val shortForecast: String = "missing"
)

