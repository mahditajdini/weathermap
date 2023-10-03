package com.the_tj.weather.ui.main

import com.the_tj.weather.base.BasePresenter
import com.the_tj.weather.data.model.ItemModel

interface MainContracts {
    interface View{
        fun showAllItems(itemModel: List<ItemModel>)
        fun showEmpty()
    }
    interface Presenter : BasePresenter {
        fun loadAllItems()
    }
}