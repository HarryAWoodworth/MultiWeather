package com.harryawoodworth.multiweather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.harryawoodworth.multiweather.R
import com.harryawoodworth.multiweather.data.api.WeatherService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MULTIWEATHER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherInterface = WeatherService.create()

        GlobalScope.launch {
            val result = weatherInterface.getForecast("OKX",36,36)
            if (result != null) {
                Log.d(TAG, "Result: ${result.body().toString()}")
            } else {
                Log.e(TAG, "Result is null")
            }
        }

    }



}