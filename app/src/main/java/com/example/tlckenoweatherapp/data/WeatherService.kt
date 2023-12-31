package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.data

import OpenApiResponse
import retrofit2.http.GET

interface WeatherService {
    @GET("api/olestang/weather/list")
    suspend fun getCityWeatherList(): OpenApiResponse
}
