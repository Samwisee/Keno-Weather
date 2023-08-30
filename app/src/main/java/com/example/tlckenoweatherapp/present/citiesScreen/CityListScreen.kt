package com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.domain.CityData

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
            LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
                items(cityList) { city ->
                    CityListItem(city = city, onItemClick = onCityClick)
                    Divider(color = Color.Gray)
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(city.id) }
            .padding(16.dp)
    ) {
        Text(
            text = city.cityName,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
