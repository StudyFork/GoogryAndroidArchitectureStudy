package com.deepco.studyfork.data.local

import android.content.Context
import android.content.SharedPreferences
import com.deepco.studyfork.data.model.RecentSearchData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val MOVIE: String = "movie"
private const val PREF_RECENT_SEARCH: String = "pref_recent_search"

class SharedPreferenceUtil(context: Context) {
    private val preference: SharedPreferences = context.getSharedPreferences(MOVIE, 0)
    private var gson = Gson()
    fun saveQuery(queryList: List<RecentSearchData>) {
        preference.edit().putString(PREF_RECENT_SEARCH, gson.toJson(queryList).toString()).apply()
    }

    fun getQueryList(): List<RecentSearchData> {
        val json = preference.getString(PREF_RECENT_SEARCH, null)
        var queryList = listOf<RecentSearchData>()
        val type = object : TypeToken<List<RecentSearchData?>?>() {}.type
        if (json != null) {
            queryList = gson.fromJson(json, type)
        }

        return queryList
    }

}
