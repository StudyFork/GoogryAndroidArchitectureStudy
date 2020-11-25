package com.deepco.studyfork.data.local

import com.deepco.studyfork.MyApplication
import com.deepco.studyfork.data.model.RecentSearchData

class LocalMovieDataImpl : LocalMovieData {
    override fun saveQuery(query: String) {
        val queryList = getQueryList().toMutableList()
        queryList.add(RecentSearchData(query))

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }
        saveQueryList(queryList)
    }

    private fun saveQueryList(query: List<RecentSearchData>) {
        MyApplication.prefs.saveQuery(query)
    }

    override fun getQueryList(): List<RecentSearchData> =
        MyApplication.prefs.getQueryList()
}