package com.harryawoodworth.multiweather.data.model

import com.google.gson.annotations.SerializedName

/**
 * Model for the data returned by the Weather API
 * Useful data is in the properties model
 */
data class WeatherResponseModel(
    @SerializedName("properties")
    var properties: WeatherPropertiesModel = WeatherPropertiesModel()
)
