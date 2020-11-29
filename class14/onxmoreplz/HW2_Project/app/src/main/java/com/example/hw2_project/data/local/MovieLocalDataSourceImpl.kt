package com.example.hw2_project.data.local

import com.example.hw2_project.App
import com.google.gson.Gson
import org.json.JSONArray

class MovieLocalDataSourceImpl : MovieLocalDataSource {
    override fun saveQuery(query: String) {
        val queryList = getSavedQuery().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }
        App.prefs.saveQueryList(queryList)
    }

    override fun getSavedQuery(): List<String> {
        val json = App.prefs.getSavedQueryListTest()
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