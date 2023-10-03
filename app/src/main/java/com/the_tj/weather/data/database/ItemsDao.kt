package com.the_tj.weather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.utils.ITEMS_TABLE
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CompletableDeferred

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItem(item:ItemModel): Completable

    @Query("SELECT * FROM $ITEMS_TABLE")
    fun getAllItems(): Observable<List<ItemModel>>

    @Query("SELECT * FROM $ITEMS_TABLE WHERE id == :id")
    fun getItem(id: Int): Observable<ItemModel>
}