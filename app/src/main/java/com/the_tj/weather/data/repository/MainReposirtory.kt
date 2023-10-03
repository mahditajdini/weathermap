package com.the_tj.weather.data.repository

import com.the_tj.weather.data.database.ItemsDao
import javax.inject.Inject

class MainReposirtory @Inject constructor(private val dao: ItemsDao) {
    fun loadAllItems() =dao.getAllItems()
}