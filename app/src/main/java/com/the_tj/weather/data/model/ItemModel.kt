package com.the_tj.weather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.the_tj.weather.utils.ITEMS_TABLE
import org.intellij.lang.annotations.PrintFormat

@Entity(tableName = ITEMS_TABLE)
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var latitude: Double= 0.0,
    var longitude: Double= 0.0,
    var temp:Double= 0.0,
    var description:String=""
)
