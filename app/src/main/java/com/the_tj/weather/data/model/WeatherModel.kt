package com.the_tj.weather.data.model

data class WeatherModel(
    val main: Main,
    val weather: List<Weather>,
)
data class Main(
    val temp: Double,

)

data class Weather(
    val description: String,

)




