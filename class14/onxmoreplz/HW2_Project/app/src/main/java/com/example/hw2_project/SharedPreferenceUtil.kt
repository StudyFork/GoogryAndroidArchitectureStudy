package com.example.hw2_project

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import org.json.JSONArray

class SharedPreferenceUtil(context: Context) {
    private val settings: SharedPreferences =
        context.getSharedPreferences("recentMovieFile", MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = settings.edit()

    fun saveQueryList(queryList: MutableList<String>) {
        editor.putString("recent_movie_data", JSONArray(queryList).toString()).apply()
    }

    fun getSavedQueryListTest(): String? =
        settings.getString("recent_movie_data", null)
}