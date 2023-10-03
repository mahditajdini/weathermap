package com.the_tj.weather.data.repository

import com.the_tj.weather.data.database.ItemsDao
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.data.server.ApiServices
import com.the_tj.weather.utils.API_KEY
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:ApiServices,private val dao:ItemsDao){
    fun getWeather(lat:Double,lng:Double)=api.getWeather(lat,lng, API_KEY)
    fun saveItem(item:ItemModel)=dao.saveItem(item)
}