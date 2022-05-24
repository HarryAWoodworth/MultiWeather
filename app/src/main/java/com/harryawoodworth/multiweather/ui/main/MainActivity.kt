package com.harryawoodworth.multiweather.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.harryawoodworth.multiweather.R
import com.harryawoodworth.multiweather.data.api.WeatherService
import com.harryawoodworth.multiweather.data.model.ForecastModel
import com.harryawoodworth.multiweather.data.repository.WeatherViewModel
import com.harryawoodworth.multiweather.databinding.ActivityMainBinding
import com.harryawoodworth.multiweather.ui.main.adapter.ForecastAdapter
import com.harryawoodworth.multiweather.utils.PermissionsManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        // Logging tag
        const val TAG = "MULTIWEATHER"
    }

    // View Binding
    private lateinit var binding: ActivityMainBinding

    // RecyclerView Adapter
    private lateinit var forecastAdapter: ForecastAdapter
    // Empty forecast list
    private var forecastList: MutableList<ForecastModel> = mutableListOf()

    // Create a ViewModel the first time this Activity is created
    // Re-created Activities will receive this same instance
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d(TAG, "Setting up empty recycler view...")

        // Set up empty recyclerview
        forecastAdapter = ForecastAdapter(forecastList)
        binding.recyclerView.adapter = forecastAdapter

        // Ask for permissions, make call to api, get forecastModel instance
        getLocalWeather()

    }

    /**
     * Get the local weather forecast as a forecastModel instance from the weatherViewModel.
     */
    private fun getLocalWeather() {
        Log.d(TAG, "Getting local weather...")
        // Check/Ask for Location permissions
        if (!PermissionsManager.isPermissionGranted(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            PermissionsManager.checkPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            Log.d(TAG, "Location permissions given, gethering local forecast...")
            // Get the local weather forecast
            weatherViewModel.getLocalWeatherForecast().observe(this, Observer<ForecastModel> {
                Log.d(TAG, "Weather Forecast Result: $it")
                // Clear the forecast list, add the current local forecast, and notify the adapter
                forecastList.clear()
                forecastList.add(it)
                forecastAdapter.notifyItemChanged(0)
            })
        }
    }

//    GlobalScope.launch {
//        val result = weatherInterface.getGridpointAPIEndpoint(40.7512,-73.8903)
//        if (result != null) {
//            Log.d(TAG, "Result: ${result.body().toString()}")
//        } else {
//            Log.e(TAG, "Result is null")
//        }
//    }
//
//    GlobalScope.launch {
//
//        val locationManager = getSystemService(Context.LOCATION_SERVICE)
//
//
//        val result = weatherInterface.getForecast("OKX",36,36)
//        if (result != null) {
//            Log.d(TAG, "Result: ${result.body().toString()}")
//        } else {
//            Log.e(TAG, "Result is null")
//        }
//    }


    /**
     * Called when a user accepts or declines a permission.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            when(requestCode) {
                PermissionsManager.COARSE_LOCATION_CODE ->  {
                    Log.d(TAG, "Coarse Location Permission Granted")
                    // Re-run getLocalWeather() once permission has been granted
                    getLocalWeather()
                }
                PermissionsManager.FINE_LOCATION_CODE ->  Log.d(TAG, "Fine Location Permission Granted")
            }
        }
    }



}