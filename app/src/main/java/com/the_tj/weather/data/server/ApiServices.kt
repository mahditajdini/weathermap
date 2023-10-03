package com.the_tj.weather.data.server

import com.the_tj.weather.data.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.rxjava3.core.Single

interface ApiServices {
    @GET("weather")
    fun getWeather(@Query("lat") latitude: Double,
                   @Query("lon") longitude: Double,
                   @Query("appid") apiKey: String)
    :Single<Response<WeatherModel>>
}