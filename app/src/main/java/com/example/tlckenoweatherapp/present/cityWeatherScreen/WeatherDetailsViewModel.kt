package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityWeatherScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepository
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherData?>()
    val weatherData: LiveData<WeatherData?> = _weatherData

    private val _cityName = MutableLiveData<String?>()
    val cityName: LiveData<String?> = _cityName

    fun fetchWeatherDetails(cityId: Int) {
        viewModelScope.launch {
            val weatherDetails = repository.getCityWeatherDetail(cityId)
            _weatherData.value = weatherDetails
            _cityName.value = weatherDetails?.cityName
        }
    }
}
