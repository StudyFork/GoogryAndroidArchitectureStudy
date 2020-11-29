package com.example.studyfork.data.local

import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONException


class LocalDataSourceImpl(private val pref: SharedPreferences) : LocalDataSource {

    override fun putRecentSearchList(item: String) {
        val items = ArrayList(getRecentSearchList())
        if (items.size >= 5) {
            items.removeAt(0)
        }
        items.add(item)
        val a = JSONArray()
        for (i in 0 until items.size) {
            a.put(items[i])
        }
        if (items.isNotEmpty()) {
            pref.edit().putString(RECENT_SEARCH_LIST, a.toString()).apply()
        } else {
            pref.edit().putString(RECENT_SEARCH_LIST, null).apply()
        }
    }

    override fun getRecentSearchList(): List<String> {
        val json = pref.getString(RECENT_SEARCH_LIST, null)
        val items = ArrayList<String>()
        if (json != null) {
            try {
                val a = JSONArray(json)
                for (i in 0 until a.length()) {
                    val item = a.optString(i)
                    items.add(item)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return items
    }

    companion object {
        const val RECENT_SEARCH_LIST = "RECENT_SEARCH_LIST"
    }
}