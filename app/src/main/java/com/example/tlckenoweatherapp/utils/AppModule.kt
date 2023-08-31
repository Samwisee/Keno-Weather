package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.utils

import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.data.WeatherService
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepository
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.WeatherRepositoryImpl
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.citiesScreen.CityListViewModel
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityWeatherScreen.WeatherDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://testapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<WeatherService> {
        get<Retrofit>().create(WeatherService::class.java)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }

    viewModel { CityListViewModel(get()) }
    viewModel { WeatherDetailsViewModel(get()) }
}
