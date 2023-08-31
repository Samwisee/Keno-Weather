package com.example.tlckenoweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.citiesScreen.CityListViewModel
import com.example.tlckenoweatherapp.com.example.tlckenoweatherapp.present.cityWeatherScreen.WeatherDetailsScreen
import com.example.tlckenoweatherapp.present.citiesScreen.CityListScreen
import com.example.tlckenoweatherapp.ui.theme.TLCKenoWeatherAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    companion object {
        private const val CITY_LIST_ROUTE = "cityList"
        private const val CITY_WEATHER_DETAIL_ROUTE = "cityWeatherDetail/{cityId}"
    }

    private val viewModel: CityListViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TLCKenoWeatherAppTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Keno Weather App",
                                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
                                )
                            },
                        )
                        NavHost(navController = navController, startDestination = CITY_LIST_ROUTE) {
                            composable(CITY_LIST_ROUTE) {
                                CityListScreen(viewModel) { cityId ->
                                    val route = CITY_WEATHER_DETAIL_ROUTE.replace(
                                        "{cityId}",
                                        cityId.toString()
                                    )
                                    navController.navigate(route)
                                }
                            }
                            composable(
                                CITY_WEATHER_DETAIL_ROUTE,
                                arguments = listOf(navArgument("cityId") { type = NavType.IntType })
                            ) { backStackEntry ->
                                backStackEntry.arguments?.getInt("cityId")?.let { cityId ->
                                    WeatherDetailsScreen(cityId = cityId, navController = navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
