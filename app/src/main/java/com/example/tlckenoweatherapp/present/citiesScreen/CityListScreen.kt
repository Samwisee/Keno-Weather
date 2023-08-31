package com.example.tlckenoweatherapp.present.citiesScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.citiesScreen.CityListViewModel
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.citiesScreen.DataState

@Composable
fun CityListScreen(viewModel: CityListViewModel, onCityClick: (Int) -> Unit) {
    val cityList by viewModel.cityList.observeAsState(initial = emptyList())
    val dataState by viewModel.dataState.observeAsState(initial = DataState.LOADING)

    when (dataState) {
        DataState.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        DataState.LOADED -> {
            LazyColumn(
                modifier = Modifier.padding(8.dp)
            ) {
                items(cityList) { city ->
                    CityListItem(city = city, onItemClick = onCityClick)
                    Divider()
                }
            }
        }
        DataState.ERROR -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("An error occurred!")
            }
        }
    }
}

@Composable
fun CityListItem(city: CityData, onItemClick: (Int) -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onItemClick(city.id) },
        headlineContent = {
            Text(
                text = city.cityName,
            )
        }
    )
}
