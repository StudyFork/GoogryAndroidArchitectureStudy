package com.example.myapplication

import androidx.room.TypeConverter
import com.example.myapplication.data.model.MovieResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {
    @TypeConverter
    fun stringToList(value: String): List<MovieResult.Item> {
        val listType = object : TypeToken<List<MovieResult.Item>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<MovieResult.Item>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}