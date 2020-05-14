package com.example.architecture.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppUtil {
    fun <T> convertGsonToJson(gsonData: List<T>): String {
        return Gson().toJson(gsonData)
    }

    fun <T> convertJsonToGson(jsonData: String): List<T> {
        val collectionType = object : TypeToken<List<T>>() {}.type

        return Gson().fromJson(jsonData, collectionType)
    }


}