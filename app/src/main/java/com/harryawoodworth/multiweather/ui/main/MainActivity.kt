package com.harryawoodworth.multiweather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.harryawoodworth.multiweather.R
import com.harryawoodworth.multiweather.data.WeatherService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val weatherInterface = WeatherService.create().getForecast()

    }



}