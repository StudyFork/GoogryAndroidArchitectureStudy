package com.example.hw2_project

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

class SharedPreferenceUtil(context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences("recentSearchedMovie", 0) //"prefs"

    fun saveQuery(query: String) {
        val queryList = getSavedQueryList().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }

        preference.edit().putString("pref_query_list", JSONArray(queryList).toString()).apply()

    }

    fun getSavedQueryList(): List<String> {
        val json = preference.getString("pref_query_list", null)
        val queryList = mutableListOf<String>()

        json?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                queryList.add(jsonArray[i].toString())
            }
        }
        return queryList
    }
}