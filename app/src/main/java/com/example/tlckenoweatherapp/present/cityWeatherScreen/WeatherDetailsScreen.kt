package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityWeatherScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsScreen(cityId: Int, navController: NavController) {
    val weatherDetailsViewModel: WeatherDetailsViewModel = getViewModel()
    weatherDetailsViewModel.fetchWeatherDetails(cityId)
    val weatherData by weatherDetailsViewModel.weatherData.observeAsState()
    val cityName by weatherDetailsViewModel.cityName.observeAsState("Loading...")

    TopAppBar(
        title = { cityName?.let { Text(it) } },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (weatherData) {
            null -> {
                CircularProgressIndicator()
            }
            else -> {
                val weatherDetails = listOf(
                    "Temperature: ${weatherData?.temperature}°C",
                    "Min-Max: ${weatherData?.minTemperature}°C - ${weatherData?.maxTemperature}°C",
                    "Pressure: ${weatherData?.pressure} hPa",
                    "Humidity: ${weatherData?.humidity}%",
                    "Description: ${weatherData?.description}",
                    "Wind Speed: ${weatherData?.windSpeed} m/s",
                    "Wind Degree: ${weatherData?.windDegree}°",
                    "Cloudiness: ${weatherData?.clouds}%",
                    "Rain: ${weatherData?.rain ?: "N/A"} mm"
                )

                LazyColumn {
                    items(weatherDetails) { detail ->
                        ListItem(
                            headlineContent = {
                                Text(text = detail)
                            }
                        )
                    }
                }
            }
        }
    }
}
