package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.data

data class OpenApiResponse(
    val calctime: Double,
    val cnt: Int,
    val list: List<CityResponse>
)

data class Coors(
    val lat: Double?,
    val lon: Double?
)

data class Wind(
    val speed: Double?,
    val deg: Double?
)

data class Rain(
    val threeHour: Double?
)

data class Clouds(
    val all: Int?
)

data class CityResponse(
    val id: Int,
    val coors: Coors?,
    val name: String,
    val weather: List<Weather>?
) {
    data class Weather(
        val temp: Double?,
        val pressure: Double?,
        val humidity: Int?,
        val temp_min: Double?,
        val temp_max: Double?,
        val weatherId: Int?,
        val main: String?,
        val description: String?,
        val icon: String?,
        val rain: Rain?,
        val clouds: Clouds?,
        val wind: Wind?
    )
}