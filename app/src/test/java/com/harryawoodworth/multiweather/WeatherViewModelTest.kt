package com.harryawoodworth.multiweather

import androidx.lifecycle.Observer
import com.harryawoodworth.multiweather.data.api.WeatherService
import com.harryawoodworth.multiweather.data.model.ForecastModel
import com.harryawoodworth.multiweather.ui.main.viewmodel.WeatherViewModel
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit tests
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    // ViewModel
    private lateinit var viewModel: WeatherViewModel

    // WeatherService instance
    private lateinit var weatherService: WeatherService

    // Mock observer
    // @Mock annotation is used to create a mock object
    @Mock
    private lateinit var forecastObserver: Observer<ForecastModel?>

    // Mock web server
    private lateinit var mockWebServer: MockWebServer

    /**
     * Allocate resources needed to run tests.
     * '@Before' means this function will run before each test.
     */
    @Before
    fun setup() {

        // Create a new ViewModel and attach an observer
        viewModel = WeatherViewModel()
        viewModel.getLocalWeatherForecast().observeForever(forecastObserver)

        // Create a mock web server instance & start it
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Create a weatherService instance
        weatherService = WeatherService.create()

    }

    /**
     * Release any allocated resources.
     * '@After' means this function will run after each test.
     */
    @After
    fun tearDown() {

        // Remove the forecast observer
        viewModel.getLocalWeatherForecast().removeObserver(forecastObserver)

        // Shut down the mock web server
        mockWebServer.shutdown()

    }

}