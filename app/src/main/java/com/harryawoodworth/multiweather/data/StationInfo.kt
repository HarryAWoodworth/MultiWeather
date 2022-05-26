package com.harryawoodworth.multiweather.data

/**
 * Represents the information needed to make an API call to get forecasts for a local area
 */
data class StationInfo (val stationName: String, val gridX: Int, val gridY: Int)