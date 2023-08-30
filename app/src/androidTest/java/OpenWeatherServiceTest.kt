package com.example.tlckenoweatherapp

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.data.WeatherService
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherData
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Call
import retrofit2.Response

class OpenWeatherServiceTest {

    @Test
    fun testGetCityList() {
        // Mock the WeatherService
        val mockService = mock(WeatherService::class.java)

        // Mock the API response
        val mockCall: Call<List<`DomainModels.kt`>> = mock(Call::class.java) as Call<List<`DomainModels.kt`>>
        val mockResponse = Response.success(
            listOf(
                `DomainModels.kt`(1, "New York", listOf(WeatherData(1, 20.0, "Sunny"))),
                `DomainModels.kt`(2, "San Francisco", listOf(WeatherData(2, 18.0, "Cloudy")))
            )
        )

        `when`(mockService.getCityList()).thenReturn(mockCall)
        `when`(mockCall.execute()).thenReturn(mockResponse)

        // Test
        val call = mockService.getCityList()
        val response = call.execute()
        assertEquals(2, response.body()?.size)
    }
}