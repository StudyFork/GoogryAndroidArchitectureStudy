package com.example.myproject.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray


class Preferences(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)

    fun saveData(data: String) {
        val list = readData()
        list.add(data)
        if (list.size > 5) {
            list.removeAt(0)
        }
        val jsonArray = JSONArray(list)
        preferences.edit().putString("SearchList", jsonArray.toString()).apply()
    }

    fun readData(): ArrayList<String> {
        val json = preferences.getString("SearchList", "EMPTY")
        val dataList = arrayListOf<String>()

        if (json != "EMPTY") {
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                dataList.add(jsonArray[i].toString())
            }
        }

        return dataList
    }

}
