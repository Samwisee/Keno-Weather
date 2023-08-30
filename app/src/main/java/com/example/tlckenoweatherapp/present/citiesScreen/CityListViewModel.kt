package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityscreen

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepository
import kotlinx.coroutines.launch

enum class DataState {
    LOADING, LOADED, ERROR
}

class CityListViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _cityList = MutableLiveData<List<CityData>>()
    val cityList: LiveData<List<CityData>> = _cityList

    private val _dataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = _dataState

    init {
        fetchData()
    }

    @VisibleForTesting
    fun fetchData() {
        viewModelScope.launch {
            _dataState.postValue(DataState.LOADING)
            try {
                val cities = repository.getCityList()
                _cityList.postValue(cities)
                _dataState.postValue(DataState.LOADED)
            } catch (e: Exception) {
                Log.e("CityListViewModel", "Error fetching data: ${e.message}", e)
                _dataState.postValue(DataState.ERROR)
            }
        }
    }
}
