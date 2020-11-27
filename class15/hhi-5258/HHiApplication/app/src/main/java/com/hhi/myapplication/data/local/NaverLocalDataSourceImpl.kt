package com.hhi.myapplication.data.local

import com.hhi.myapplication.App
import org.json.JSONArray

private const val PREF_QUERY_LIST: String = "pref_query_list"

class NaverLocalDataSourceImpl : NaverLocalDataSource {
    override fun saveQuery(query: String) {
        val queryList = getQueryList().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }

        App.prefs.setString(JSONArray(queryList).toString(), PREF_QUERY_LIST)

    }

    override fun getQueryList(): List<String> {
        val queryListJSONString = App.prefs.getString(PREF_QUERY_LIST)
        val queryList = mutableListOf<String>()

        queryListJSONString?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                queryList.add(jsonArray.getString(i))
            }
        }
        return queryList
    }
}