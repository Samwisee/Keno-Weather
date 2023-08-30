package com.example.tlckenoweatherapp

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.data.*
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WeatherRepositoryImplTest {

    private val mockWeather = CityResponse.Weather(
        temp = 25.0,
        pressure = 1013.0,
        humidity = 60,
        temp_min = 20.0,
        temp_max = 30.0,
        weatherId = 1,
        main = "Clear",
        description = "Clear sky",
        icon = "01d",
        rain = Rain(0.0),
        clouds = Clouds(0),
        wind = Wind(5.0, 180.0)
    )

    private val mockCityResponse = CityResponse(
        id = 1,
        coors = Coors(37.7749, -122.4194),
        name = "San Francisco",
        weather = mockWeather
    )

    private val mockOpenApiResponse = OpenApiResponse(
        calctime = 0.1,
        cnt = 1,
        list = listOf(mockCityResponse)
    )

    private val mockWeatherService = object : WeatherService {
        override suspend fun getCityWeatherList(): OpenApiResponse = mockOpenApiResponse
    }

    private val repository = WeatherRepositoryImpl(mockWeatherService)

    @Test
    fun getCityList_returnsCorrectData() = runBlocking {
        val expectedCityData = CityData(
            id = 1,
            cityName = "San Francisco",
            longitude = 37.7749,
            latitude = -122.4194,
            weatherData = WeatherData(
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
        )

        val expected = listOf(expectedCityData)
        val actual = repository.getCityList()
        assertEquals(expected, actual)
    }
}
