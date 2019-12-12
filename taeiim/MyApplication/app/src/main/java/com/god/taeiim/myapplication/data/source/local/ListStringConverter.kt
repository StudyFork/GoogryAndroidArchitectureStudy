package com.god.taeiim.myapplication.data.source.local

import androidx.room.TypeConverter
import com.god.taeiim.myapplication.api.model.SearchResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringConverter {

    @TypeConverter
    fun stringToList(value: String): List<SearchResult.Item> {
        val listType = object : TypeToken<List<SearchResult.Item>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<SearchResult.Item>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}
