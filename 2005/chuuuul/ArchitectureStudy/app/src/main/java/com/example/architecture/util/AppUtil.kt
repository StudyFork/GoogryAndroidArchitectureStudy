package com.example.architecture.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppUtil {
    fun <T> convertGsonToJson(gsonData: Collection<T>): String {
        return Gson().toJson(gsonData)
    }

    inline fun <reified T> convertJsonToGson(jsonData: String): Collection<T> {
        val collectionType = TypeToken.getParameterized(Collection::class.java, T::class.java).type
        return Gson().fromJson(jsonData, collectionType)
    }
}