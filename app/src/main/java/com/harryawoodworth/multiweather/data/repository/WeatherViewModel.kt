package com.harryawoodworth.multiweather.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harryawoodworth.multiweather.data.api.WeatherService
import com.harryawoodworth.multiweather.data.model.ForecastModel
import kotlinx.coroutines.launch

/**
 * ViewModel class designed to store weather forecast data.
 */
class WeatherViewModel: ViewModel() {

    // Create a weatherService instance
    private var weatherService = WeatherService.create()

    /**
     * Use LiveData so that the data can be observed in a lifecycle-safe way.
     * Use 'by lazy{}' to compute the value upon first access using loadLocalWeatherForecast().
     */
    private val localForecast: MutableLiveData<ForecastModel> by lazy {
        // We use '.also' to set localForecast by calling loadLocalWeatherForecast
        // also takes the calling object as an argument and returns the calling object
        MutableLiveData<ForecastModel>().also {
            loadLocalWeatherForecast()
        }
    }

    /**
     * Return the current state of localForecast.
     */
    fun getLocalWeatherForecast(): LiveData<ForecastModel> {
        return localForecast
    }

    /**
     * Use a Coroutine in the ViewModelScope to fetch the local forecast using WeatherService.
     * https://developer.android.com/topic/libraries/architecture/coroutines
     */
    private fun loadLocalWeatherForecast() {
        viewModelScope.launch {
            // Get the API result
            val weatherResult = weatherService.getForecast("OKX",36,36)
            // Get the current local forecast and set localForecast
            val forecastPeriods = weatherResult?.body()?.properties?.periods
            if (forecastPeriods != null && forecastPeriods.isNotEmpty()) {
                // kotlin calls LiveData's setValue(T) function when accessing the value
                // This will update the observables
                localForecast.value = forecastPeriods[0]
            }
        }
    }

}