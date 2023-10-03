package com.the_tj.weather.ui.weather

import com.the_tj.weather.base.BasePresenter
import com.the_tj.weather.base.BaseView
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.data.model.LocationModel
import com.the_tj.weather.data.model.WeatherModel

interface WeatherContracts {
    interface View:BaseView{
        fun close()
        fun showWeather(weatherModel: WeatherModel)
        fun getWeather()

    }
    interface Presenter: BasePresenter {

        fun callWeather(location: LocationModel)
        fun saveItem(item:ItemModel)
    }
}