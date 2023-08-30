package com.example.tlckenoweatherapp

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepository
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityweatherscreen.WeatherDetailsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WeatherDetailsViewModelTest {

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

    @Test
    fun fetchWeatherDetails_callsRepository() = runBlocking {
        val cityId = 1
        val expected = mockRepository.getCityWeatherDetail(cityId)
        viewModel.fetchWeatherDetails(cityId)
        val actual = viewModel.weatherData.value
        assertEquals(expected, actual)
    }
}
