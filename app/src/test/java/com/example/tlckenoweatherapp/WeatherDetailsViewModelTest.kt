package com.example.tlckenoweatherapp

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepository
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityWeatherScreen.WeatherDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class WeatherDetailsViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val mockWeatherData = WeatherData(
        id = 1,
        temperature = 25.0,
        minTemperature = 20.0,
        maxTemperature = 30.0,
        pressure = 1013.0,
        humidity = 60,
        description = "Clear sky",
        icon = "01d",
        rain = 0.0,
        windSpeed = 5.0,
        windDegree = 180.0,
        clouds = 0
    )

    private val mockRepository = object : WeatherRepository {
        override suspend fun getCityList(): List<CityData> = emptyList()
        override suspend fun getCityWeatherDetail(cityId: Int): WeatherData? = mockWeatherData
    }

    private val viewModel = WeatherDetailsViewModel(mockRepository)


    // This test checks if the ViewModel correctly fetches weather details for a given city ID and updates its LiveData object with the fetched data.
    @Test
    fun fetchWeatherDetails_callsRepository() = runBlocking {
        val cityId = 1
        val expected = mockRepository.getCityWeatherDetail(cityId)
        viewModel.fetchWeatherDetails(cityId)
        val actual = viewModel.weatherData.value
        assertEquals(expected, actual)
    }


    // This test checks how the ViewModel handles errors, e.g., when the repository returns null.
    @Test
    fun fetchWeatherDetails_handlesError() = runBlocking {
        val cityId = 1
        val expected = null
        val errorRepository = object : WeatherRepository {
            override suspend fun getCityList(): List<CityData> = emptyList()
            override suspend fun getCityWeatherDetail(cityId: Int): WeatherData? = null
        }
        val errorViewModel = WeatherDetailsViewModel(errorRepository)
        errorViewModel.fetchWeatherDetails(cityId)
        val actual = errorViewModel.weatherData.value
        assertEquals(expected, actual)
    }
}
