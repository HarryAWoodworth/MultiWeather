package com.harryawoodworth.multiweather.data.model

import com.google.gson.annotations.SerializedName

/**
 * Model for the properties member of the WeatherGridpointModel
 * Contains a list of ForecastModel objects
 */
data class WeatherPropertiesModel(
    @SerializedName("periods")
    val periods: List<ForecastModel> = listOf()
)
