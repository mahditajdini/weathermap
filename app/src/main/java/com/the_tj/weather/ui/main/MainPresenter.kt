package com.the_tj.weather.ui.main

import android.util.Log
import com.the_tj.weather.base.BasePresenter
import com.the_tj.weather.base.BasePresenterImp
import com.the_tj.weather.data.repository.MainReposirtory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository:MainReposirtory,private val view: MainContracts.View) :
MainContracts.Presenter,BasePresenterImp(){
    override fun loadAllItems() {
        disposable=repository.loadAllItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotEmpty()) {
                    Log.e("mytag","repository load all items is not empty")
                    view.showAllItems(it)
                } else {
                    Log.e("mytag","repository load all items is  empty")
                    view.showEmpty()
                }
            }
    }
}