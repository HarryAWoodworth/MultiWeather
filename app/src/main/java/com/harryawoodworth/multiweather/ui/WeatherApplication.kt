package com.harryawoodworth.multiweather.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class annotated with @HiltAndroidApp so that it can serve as the application-level
 * dependency container.
 */
@HiltAndroidApp
class WeatherApplication : Application()



