package com.harryawoodworth.multiweather.ui.main.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.harryawoodworth.multiweather.data.StationInfo
import com.harryawoodworth.multiweather.data.database.ForecastDatabase
import com.harryawoodworth.multiweather.data.repository.ForecastRepository
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel class designed to store weather forecast data.
 */
class WeatherViewModel(application: Application, stationInfo: StationInfo): AndroidViewModel(application) {

    companion object {
        private const val TAG = "MULTIWEATHER"
    }

    // Forecast Repository instance
    // This is the data source the forecasts are gotten from
    private val forecastRepository = ForecastRepository(ForecastDatabase.getInstance(application), stationInfo)

    // Access the local forecasts from the repository
    // Use LiveData so that the data can be observed in a lifecycle-safe way.
    val localForecasts = forecastRepository.forecasts

    // Refresh the forecasts upon initialization
    init {
        refreshForecastData()
    }

    // Use a Coroutine in the ViewModelScope to refresh forecasts in the repository
    private fun refreshForecastData() {
        viewModelScope.launch {
            try {
                forecastRepository.refreshForecasts()
            } catch (networkError: IOException) {
                Log.e(TAG, "Network error when refreshing forecast data: ${networkError.localizedMessage}")
            }
        }
    }

    // We need a factory because we are creating a ViewModel that takes arguments
    class Factory(private val app: Application, private val stationInfo: StationInfo) : ViewModelProvider.Factory {
        // Create a new ViewModel
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                @Suppress("UNCHECKED CAST")
                return WeatherViewModel(app, stationInfo) as T
            }
            // If the ViewModel is not constructed, throw an error
            throw IllegalArgumentException("Unable to construct WeatherViewModel.")
        }
    }

}