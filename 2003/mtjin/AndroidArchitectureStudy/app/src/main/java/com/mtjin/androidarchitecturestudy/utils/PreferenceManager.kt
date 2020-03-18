package com.mtjin.androidarchitecturestudy.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(private val context: Context) {

    private fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences(MOVIE_SEARCH_APP, Context.MODE_PRIVATE)
    }

    fun setBoolean(value: Boolean) {
        val prefs = getPreferences()
        val editor = prefs.edit()
        editor.putBoolean(AUTO_LOGIN_KEY, value)
        editor.apply()
    }

    fun getBoolean(): Boolean {
        val prefs = getPreferences()
        return prefs.getBoolean(AUTO_LOGIN_KEY, false)
    }

    companion object {
        private const val MOVIE_SEARCH_APP = "MOVIE_SEARCH_APP"
        const val AUTO_LOGIN_KEY = "AUTO_LOGIN_KEY"
    }
}