package com.spacexapidemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spacexapidemo.data.db.converters.EnginesConverter
import com.spacexapidemo.data.db.converters.JsonToStringConverter
import com.spacexapidemo.data.entity.RocketsEntity

@Database(entities = [RocketsEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    JsonToStringConverter::class,
    EnginesConverter::class
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun rocketDao(): RocketDao
}