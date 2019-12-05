package com.egiwon.architecturestudy.data.source.local.model

import androidx.room.TypeConverter
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContentTypeConvertor {

    private val gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<ContentItem> = when (data) {
        null -> emptyList()
        else -> gson.fromJson<List<ContentItem>>(
            data,
            object : TypeToken<List<ContentItem>>() {}.type
        )
    }

    @TypeConverter
    fun toString(movies: List<ContentItem>): String {
        return gson.toJson(movies)
    }
}