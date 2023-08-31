data class OpenApiResponse(
    val calctime: Double,
    val cnt: Int,
    val list: List<CityResponse>
)

data class Coors(
    val lat: Double?,
    val lon: Double?
)

data class Main(
    val temp: Double?,
    val pressure: Double?,
    val humidity: Int?,
    val temp_min: Double?,
    val temp_max: Double?
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

data class Weather(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class CityResponse(
    val id: Int,
    val coord: Coors?,
    val name: String,
    val main: Main?,
    val wind: Wind?,
    val rain: Rain?,
    val clouds: Clouds?,
    val weather: List<Weather>?
)
