package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.data.OpenApiResponse
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.data.WeatherService

interface WeatherRepository {
    suspend fun getCityList(): List<CityData>
    suspend fun getCityWeatherDetail(cityId: Int): WeatherData?
}

class WeatherRepositoryImpl(private val weatherService: WeatherService) : WeatherRepository {

    override suspend fun getCityList(): List<CityData> {
        val response = weatherService.getCityWeatherList()
        return mapOpenApiResponseToCityData(response)
    }

    override suspend fun getCityWeatherDetail(cityId: Int): WeatherData? {
        val allCities = getCityList()
        return allCities.find { it.id == cityId }?.weatherData
    }

    private fun mapOpenApiResponseToCityData(openApiResponse: OpenApiResponse): List<CityData> {
        return openApiResponse.list.mapNotNull { cityResponse ->
            cityResponse.weather?.firstOrNull()?.let { weather ->  // Taking the first weather object from the list
                CityData(
                    id = cityResponse.id,
                    cityName = cityResponse.name,
                    longitude = cityResponse.coors?.lon ?: 0.0,
                    latitude = cityResponse.coors?.lat ?: 0.0,
                    weatherData = WeatherData(
                        id = weather.weatherId ?: 0,
                        temperature = weather.temp ?: 0.0,
                        minTemperature = weather.temp_min ?: 0.0,
                        maxTemperature = weather.temp_max ?: 0.0,
                        pressure = weather.pressure ?: 0.0,
                        humidity = weather.humidity ?: 0,
                        description = weather.description ?: "",
                        icon = weather.icon ?: "",
                        clouds = weather.clouds?.all ?: 0,
                        rain = weather.rain?.threeHour ?: 0.0,
                        windDegree = weather.wind?.deg ?: 0.0,
                        windSpeed = weather.wind?.speed ?: 0.0
                    )
                )
            }
        }
    }
}
