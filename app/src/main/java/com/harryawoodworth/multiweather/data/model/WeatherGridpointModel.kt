package com.harryawoodworth.multiweather.data.model

import com.google.gson.annotations.SerializedName

/**
 * Model for the initial data returned from https://api.weather.gov/gridpoints/{station}/{gridX},{gridY}/forecast
 */
data class WeatherGridpointModel(
    @SerializedName("properties")
    var properties: WeatherPropertiesModel = WeatherPropertiesModel()
)
