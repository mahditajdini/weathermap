package com.the_tj.weather.ui.weather

import android.util.Log
import com.the_tj.weather.base.BasePresenter
import com.the_tj.weather.base.BasePresenterImp
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.data.model.LocationModel
import com.the_tj.weather.data.model.WeatherModel
import com.the_tj.weather.data.repository.WeatherRepository
import com.the_tj.weather.utils.applyIoScheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class WeatherPresenter @Inject constructor(
    private val repository: WeatherRepository,
    private val view: WeatherContracts.View
) :
    WeatherContracts.Presenter, BasePresenterImp() {



    override fun callWeather(location: LocationModel) {
        if (view.checkInternet()) {
            view.showLoading()
            disposable = repository.getWeather(location.latitude, location.longitude)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                if (itBody.weather != null) {
                                    if (itBody.weather.isNotEmpty()) {
                                        view.showWeather(itBody)
                                        view.getWeather()
                                        Log.e("mytag","weather response")
                                    }
                                }
                            }
                        }
                    }

                }, {
                    view.hideLoading()
                    view.serverError(it.message.toString())
                })
        } else {
            view.internetError(false)
        }
    }


    override fun saveItem(item: ItemModel) {
        disposable = repository.saveItem(item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                //view.close()
            }

    }

}