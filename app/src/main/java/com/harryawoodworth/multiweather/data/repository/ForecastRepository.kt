package com.harryawoodworth.multiweather.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.harryawoodworth.multiweather.data.StationInfo
import com.harryawoodworth.multiweather.data.api.WeatherService
import com.harryawoodworth.multiweather.data.database.ForecastDatabase
import com.harryawoodworth.multiweather.data.model.ForecastModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastRepository(private val database: ForecastDatabase, var stationInfo: StationInfo) {

    val forecasts: LiveData<List<ForecastModel>> = database.forecastDao.getAll()

    companion object { private const val TAG = "MULTIWEATHER" }

    /**
     * API used to refresh the offline cache
     */
    suspend fun refreshForecasts() {
        // We use Dispatchers.IO to perform network and database operations
        withContext(Dispatchers.IO) {
            // Get the forecasts from WeatherService
            val forecastsFromService = WeatherService.create().getForecast(
                stationInfo.stationName,
                stationInfo.gridX,
                stationInfo.gridY)
                ?.body()?.properties?.periods
            Log.d(TAG, "Forecasts from WeatherService: $forecastsFromService")
            // Save them in the repository database
            if (forecastsFromService != null) {
                database.forecastDao.insertAll(forecastsFromService)
            }
        }
    }

}