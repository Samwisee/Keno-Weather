package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain

import OpenApiResponse
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
        val cityData = allCities.find { it.id == cityId }
        cityData?.weatherData?.cityName = cityData?.cityName
        return cityData?.weatherData
    }

    private fun mapOpenApiResponseToCityData(openApiResponse: OpenApiResponse): List<CityData> {
        return openApiResponse.list.mapNotNull { cityResponse ->
            cityResponse.weather?.firstOrNull()?.let { weather ->
                CityData(
                    id = cityResponse.id,
                    cityName = cityResponse.name,
                    longitude = cityResponse.coord?.lon ?: 0.0,
                    latitude = cityResponse.coord?.lat ?: 0.0,
                    weatherData = WeatherData(
                        id = weather.id ?: 0,
                        temperature = cityResponse.main?.temp ?: 0.0,
                        minTemperature = cityResponse.main?.temp_min ?: 0.0,
                        maxTemperature = cityResponse.main?.temp_max ?: 0.0,
                        pressure = cityResponse.main?.pressure ?: 0.0,
                        humidity = cityResponse.main?.humidity ?: 0,
                        description = weather.description ?: "",
                        icon = weather.icon ?: "",
                        clouds = cityResponse.clouds?.all ?: 0,
                        rain = cityResponse.rain?.threeHour ?: 0.0,
                        windDegree = cityResponse.wind?.deg ?: 0.0,
                        windSpeed = cityResponse.wind?.speed ?: 0.0
                    )
                )
            }
        }
    }
}

