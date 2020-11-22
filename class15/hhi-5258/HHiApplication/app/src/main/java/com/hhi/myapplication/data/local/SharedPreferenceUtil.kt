package com.hhi.myapplication.data.local

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

private const val FILENAME: String = "prefs"
private const val PREF_QUERY_LIST: String = "pref_query_list"

class SharedPreferenceUtil(context: Context) {
    private val preference: SharedPreferences = context.getSharedPreferences(FILENAME, 0)

    fun saveQuery(query: String) {
        val queryList = getQueryList().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }

        preference.edit().putString(PREF_QUERY_LIST, JSONArray(queryList).toString()).apply()

    }

    fun getQueryList(): List<String> {
        val json = preference.getString(PREF_QUERY_LIST, null)
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