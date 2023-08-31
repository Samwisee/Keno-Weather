package com.example.tlckenoweatherapp

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepository
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.citiesScreen.CityListViewModel
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
class CityListViewModelTest {

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
        pressure = 1000.0,
        humidity = 50,
        description = "Clear sky",
        icon = "01d",
        rain = 0.0,
        windSpeed = 5.0,
        windDegree = 90.0,
        clouds = 0
    )

    private val mockCityData = CityData(
        id = 1,
        cityName = "Melbourne",
        longitude = -120.0000,
        latitude = -38.9999,
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
