package com.spacexapidemo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spacexapidemo.data.db.converters.EnginesConverter
import com.spacexapidemo.data.db.converters.JsonToStringConverter

@Entity(tableName = "rocket_table")
data class RocketsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids:Long,
    val active: Boolean,
    val country: String,
    @TypeConverters(EnginesConverter::class)
    val engines: Engines,
    @TypeConverters(JsonToStringConverter::class)
    val flickr_images: List<String>,
    val id: String,
    val name: String,
)