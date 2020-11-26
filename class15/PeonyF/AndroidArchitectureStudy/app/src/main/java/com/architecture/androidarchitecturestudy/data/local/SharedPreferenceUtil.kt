package com.architecture.androidarchitecturestudy.data.local

import android.content.Context
import android.content.SharedPreferences
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val MOVIE_KEYWORD: String = "movieKeyword"
private const val PREF_SEARCH_HISTORY: String = "preferenceSearchHistory"

class SharedPreferenceUtil(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(MOVIE_KEYWORD, 0)

    fun saveSearchHistory(queryList: List<SearchHistoryEntity>) {
        val listJson = Gson().toJson(queryList)
        val query = listJson.toString()
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(PREF_SEARCH_HISTORY, query).apply()
    }

    fun getSearchHistoryList(): List<SearchHistoryEntity> {
        val listJson = preferences.getString(PREF_SEARCH_HISTORY, null)
        val queryList = listOf<SearchHistoryEntity>()
        if (!listJson.isNullOrBlank()) {
            val type = object : TypeToken<List<SearchHistoryEntity>>() {}.type
            return Gson().fromJson(listJson, type)
        }
        return queryList
    }
}