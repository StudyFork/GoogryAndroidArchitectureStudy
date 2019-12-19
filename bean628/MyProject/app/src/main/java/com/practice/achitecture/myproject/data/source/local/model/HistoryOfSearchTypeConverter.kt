package com.practice.achitecture.myproject.data.source.local.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practice.achitecture.myproject.model.SearchedItem

class HistoryOfSearchTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<SearchedItem> = when (data) {
        null -> emptyList()
        else -> gson.fromJson<List<SearchedItem>>(
            data,
            object : TypeToken<List<SearchedItem>>() {}.type
        )
    }

    @TypeConverter
    fun toJsonString(movies: List<SearchedItem>): String {
        return gson.toJson(movies)
    }

}