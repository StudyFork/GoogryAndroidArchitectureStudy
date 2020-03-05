package com.mtjin.androidarchitecturestudy.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    private const val MOVIE_SEARCH_APP = "MOVIE_SEARCH_APP"
    const val AUTO_LOGIN_KEY = "AUTO_LOGIN_KEY"

    private fun getPreferences(context: Context): SharedPreferences? {
        return context.getSharedPreferences(MOVIE_SEARCH_APP, Context.MODE_PRIVATE)
    }

    fun setBoolean(context: Context, key: String, value: Boolean) {
        val prefs = getPreferences(context)
        val editor = prefs?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun getBoolean(context: Context, key: String): Boolean {
        val prefs = getPreferences(context)
        return prefs!!.getBoolean(key, false)
    }
}