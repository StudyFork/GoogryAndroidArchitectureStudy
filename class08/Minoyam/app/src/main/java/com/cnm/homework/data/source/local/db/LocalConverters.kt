package com.cnm.homework.data.source.local.db

import androidx.room.TypeConverter
import com.cnm.homework.data.model.NaverResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalConverters {
    companion object {
        val gson = Gson()

        @JvmStatic
        @TypeConverter
        fun stringToList(value: String?): List<NaverResponse.Item?>? {
            if(value.isNullOrEmpty()){
                return emptyList()
            }
            return gson.fromJson<List<NaverResponse.Item>>(value)
        }

        @JvmStatic
        @TypeConverter
        fun listToString(item : List<NaverResponse.Item>): String? {
            if(item.isEmpty()){
                return null
            }
            return gson.toJson(item)
        }
    }

}

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson(json, object: TypeToken<T>() {}.type)