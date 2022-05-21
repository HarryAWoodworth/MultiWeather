package com.harryawoodworth.multiweather

import com.harryawoodworth.multiweather.data.repository.WeatherViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel

    // private lateinit var apiHelper: ApiHelperImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewModel = WeatherViewModel()
       //  viewModel.getLocalWeatherForecast().observeForever()



    }

}