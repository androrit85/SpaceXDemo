package com.spacexapidemo.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spacexapidemo.data.entity.Engines

class EnginesConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Engines>() {}.type
    @TypeConverter
    fun enginesToString(engines: Engines): String?{
        return gson.toJson(engines, type)
    }
    @TypeConverter
    fun stringToEngines(string: String): Engines?{
        return gson.fromJson(string,type)
    }
}