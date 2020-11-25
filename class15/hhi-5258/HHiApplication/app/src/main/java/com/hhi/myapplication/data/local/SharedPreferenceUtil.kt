package com.hhi.myapplication.data.local

import android.content.Context
import android.content.SharedPreferences

private const val FILENAME: String = "prefs"

class SharedPreferenceUtil(context: Context) {
    private val preference: SharedPreferences = context.getSharedPreferences(FILENAME, 0)

    fun setString(value: String, key: String) {
        preference.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return preference.getString(key, null)
    }
}