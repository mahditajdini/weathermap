package com.the_tj.weather.base

import io.reactivex.rxjava3.disposables.Disposable

open class BasePresenterImp : BasePresenter {

    var disposable: Disposable? = null

    override fun onStop() {
        disposable?.let {
            it.dispose()
        }
    }
}