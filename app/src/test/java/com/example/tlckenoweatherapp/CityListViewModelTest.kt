package com.example.tlckenoweatherapp

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepository
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityscreen.CityListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CityListViewModelTest {

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

    private val mockCityData = CityData(
        id = 1,
        cityName = "San Francisco",
        longitude = -122.4194,
        latitude = 37.7749,
        weatherData = mockWeatherData
    )

    private val mockRepository = object : WeatherRepository {
        override suspend fun getCityList(): List<CityData> = listOf(mockCityData)
        override suspend fun getCityWeatherDetail(cityId: Int): WeatherData? = null
    }

    private val viewModel = CityListViewModel(mockRepository)

    @Test
    fun fetchData_callsRepository() = runBlocking {
        val expected = mockRepository.getCityList()
        viewModel.fetchData()
        val actual = viewModel.cityList.value
        assertEquals(expected, actual)
    }
}
