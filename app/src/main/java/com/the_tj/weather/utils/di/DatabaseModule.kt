package com.the_tj.weather.utils.di

import android.content.Context
import androidx.room.Room
import com.the_tj.weather.data.database.ItemsDatabase
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.data.model.LocationModel
import com.the_tj.weather.data.model.WeatherModel
import com.the_tj.weather.utils.ITEMS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ItemsDatabase::class.java, ITEMS_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: ItemsDatabase) = db.itemsDao()

    @Provides
    @Singleton
    fun provideEntity() = ItemModel()


}