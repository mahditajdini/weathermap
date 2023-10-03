package com.the_tj.weather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.the_tj.weather.data.model.ItemModel

@Database(entities = [ItemModel::class], version =1, exportSchema = false)
abstract class ItemsDatabase: RoomDatabase() {
    abstract fun itemsDao():ItemsDao

}