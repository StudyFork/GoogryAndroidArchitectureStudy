package com.hhi.myapplication.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(context: Context) {
    private val preference: SharedPreferences = context.getSharedPreferences(FILE_NAME, 0)

    fun setString(value: String, key: String) {
        preference.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return preference.getString(key, null) ?: ""
    }

    companion object {
        private const val FILE_NAME: String = "prefs"
    }
}