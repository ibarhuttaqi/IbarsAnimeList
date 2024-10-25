package com.example.ibarsanimelist.core.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    // Convert List<String> to a single JSON String
    @TypeConverter
    fun fromStringList(list: List<String>?): String {
        return Gson().toJson(list)
    }

    // Convert a JSON String back to a List<String>
    @TypeConverter
    fun toStringList(json: String): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }
}