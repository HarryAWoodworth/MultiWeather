package com.harryawoodworth.multiweather.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.harryawoodworth.multiweather.R
import com.harryawoodworth.multiweather.data.api.WeatherService
import com.harryawoodworth.multiweather.utils.PermissionsManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        // Logging tag
        const val TAG = "MULTIWEATHER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check/Ask for location permissions
        if (!PermissionsManager.isPermissionGranted(this,  Manifest.permission.ACCESS_COARSE_LOCATION)) {
            PermissionsManager.checkPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            testAPI()
        }

    }

    private fun testAPI() {

        val weatherInterface = WeatherService.create()

        GlobalScope.launch {

            val locationManager = getSystemService(Context.LOCATION_SERVICE)


            val result = weatherInterface.getForecast("OKX",36,36)
            if (result != null) {
                Log.d(TAG, "Result: ${result.body().toString()}")
            } else {
                Log.e(TAG, "Result is null")
            }
        }

        GlobalScope.launch {
            val result = weatherInterface.getGridpointAPIEndpoint(40.7512,-73.8903)
            if (result != null) {
                Log.d(TAG, "Result: ${result.body().toString()}")
            } else {
                Log.e(TAG, "Result is null")
            }
        }
    }


    /**
     * Called when a user accepts or declines a permission
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
                    // Re-run testAPI() once permission has been granted
                    testAPI()
                }
                PermissionsManager.FINE_LOCATION_CODE ->  Log.d(TAG, "Fine Location Permission Granted")
            }
        }
    }



}