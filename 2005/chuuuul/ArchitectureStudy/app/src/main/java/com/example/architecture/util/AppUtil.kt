package com.example.architecture.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppUtil {
    fun <T> convertGsonToJson(gsonData: Collection<T>): String {
        return Gson().toJson(gsonData)
    }

    fun <T> convertJsonToGson(jsonData: String, clazz: Class<T>): Collection<T> {
        val collectionType = TypeToken.getParameterized(Collection::class.java, clazz).type
        return Gson().fromJson(jsonData, collectionType)
    }
}