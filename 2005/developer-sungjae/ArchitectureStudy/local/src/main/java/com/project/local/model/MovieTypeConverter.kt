package com.project.local.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.data.model.MovieItem

class MovieTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun toList(data: String?): List<MovieItem> = when (data) {
        null -> emptyList()
        else -> gson.fromJson<List<MovieItem>>(
            data,
            object : TypeToken<List<MovieItem>>() {}.type
        )
    }

    @TypeConverter
    fun toString(contents: List<MovieItem>): String {
        return gson.toJson(contents)
    }
}