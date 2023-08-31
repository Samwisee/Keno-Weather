package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain

data class CityData(
    val id: Int,
    val cityName: String,
    val longitude: Double,
    val latitude: Double,
    val weatherData: WeatherData
)

data class WeatherData(
    val id: Int,
    var cityName: String? = null,
    val temperature: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Double,
    val humidity: Int,
    val description: String,
    val icon: String,
    val rain: Double,
    val windSpeed: Double,
    val windDegree: Double,
    val clouds: Int
)
