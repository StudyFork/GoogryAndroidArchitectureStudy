package com.example.aas.data.source.local

import android.content.Context
import org.json.JSONArray
import org.json.JSONException

class LocalDataSourceImpl : LocalDataSource {

    override fun saveQuery(query: String, context: Context) {
        val queryHistorySharedPreferences =
            context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        val currentState = getSavedQuery(context).toMutableList()
        if (!currentState.remove(query) && currentState.size > 4) {
            currentState.removeFirstOrNull()
        }
        currentState.add(query)

        val editor = queryHistorySharedPreferences.edit()
        val editArray = JSONArray()
        currentState.forEach {
            editArray.put(it)
        }
        editor.putString(sharedPreferencesName, editArray.toString())
        editor.apply()
    }

    override fun getSavedQuery(context: Context): List<String> {
        val queryHistorySharedPreferences =
            context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        val result = mutableListOf<String>()

        queryHistorySharedPreferences.getString(sharedPreferencesName, null)?.let {
            try {
                val arr = JSONArray(it)
                for (i in 0 until arr.length()) {
                    result.add(arr.getString(i))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return result
    }

    companion object {
        const val sharedPreferencesName = "QueryHistory"
    }
}