package com.harryawoodworth.multiweather.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.harryawoodworth.multiweather.data.StationInfo
import com.harryawoodworth.multiweather.data.model.ForecastModel
import com.harryawoodworth.multiweather.ui.main.viewmodel.WeatherViewModel
import com.harryawoodworth.multiweather.databinding.ActivityMainBinding
import com.harryawoodworth.multiweather.ui.main.adapter.ForecastAdapter
import com.harryawoodworth.multiweather.util.PermissionsManager

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
    private val weatherViewModel: WeatherViewModel by lazy {
        ViewModelProvider(
            this,
            WeatherViewModel.Factory(application, StationInfo("OKX", 36, 36))
        )[WeatherViewModel::class.java]
    }

    /**
     * Set up view binding and empty recycler view
     */
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
    }

    /**
     * Get the local weather forecast on start
     */
    override fun onStart() {
        super.onStart()
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
            weatherViewModel.localForecasts.observe(this) {
                Log.d(TAG, "Weather Forecast Result: $it")
                // Clear the forecast list, add the current local forecast, and notify the adapter
                forecastList.clear()
                if (!it.isNullOrEmpty()) {
                    forecastList.addAll(it)
                    forecastAdapter.notifyDataSetChanged()
                }
            }
        }
    }

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