package com.deepco.studyfork.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

private const val MOVIE: String = "movie"
private const val PREF_RECENT_SEARCH: String = "pref_recent_search"

class SharedPreferenceUtil(context: Context) {
    private val preference: SharedPreferences = context.getSharedPreferences(MOVIE, 0)
    private var gson = Gson()
    fun saveQuery(query: String) {
        val queryList = getQueryList().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }
        preference.edit().putString(PREF_RECENT_SEARCH, JSONArray(queryList).toString()).apply()
    }

    fun getQueryList(): List<String> {
        val json = preference.getString(PREF_RECENT_SEARCH, null)
        var queryList = listOf<String>()
        val type = object : TypeToken<List<String?>?>() {}.type
        if (json != null) {
            queryList = gson.fromJson(json, type)
        }

        return queryList
    }

}
