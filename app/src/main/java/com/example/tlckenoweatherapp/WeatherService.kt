package com.example.tlckenoweatherapp

import com.example.tlckenoweatherapp.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("weather/list")
    fun getCityWeatherList(): Call<List<WeatherData>>

    @GET("weather/{id}")
    fun getCityWeatherDetail(@Path("id") id: Int): Call<WeatherData>
}