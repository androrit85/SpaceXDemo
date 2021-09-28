package com.spacexapidemo.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spacexapidemo.data.entity.Links

class LinksConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Links>() {}.type
    @TypeConverter
    fun linksToString(links: Links): String?{
        return gson.toJson(links, type)
    }
    @TypeConverter
    fun stringToLinks(string: String): Links?{
        return gson.fromJson(string,type)
    }
}