package com.spacexapidemo.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonToStringConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToJson(string: String): MutableList<String> {
        val type = object : TypeToken<MutableList<String>>(){}.type

        return gson.fromJson(string, type)
    }
    @TypeConverter
    fun jsonToString(json :MutableList<String>) : String{
        return  gson.toJson(json)
    }
}