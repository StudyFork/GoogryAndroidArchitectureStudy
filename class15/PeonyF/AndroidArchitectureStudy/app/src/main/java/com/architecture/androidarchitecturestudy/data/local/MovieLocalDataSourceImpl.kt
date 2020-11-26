package com.architecture.androidarchitecturestudy.data.local

import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.util.App
import org.json.JSONException


class MovieLocalDataSourceImpl : MovieLocalDataSource {
    override fun saveSearchHistory(keyword: String) {
        val searchHistoryList = getSearchHistoryList().toMutableList()
        try {
            if ((searchHistoryList.size) > 5) {
                searchHistoryList.removeAt(0)
            }
            searchHistoryList.add(SearchHistoryEntity(keyword))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        saveSearchHistoryList(searchHistoryList)
    }

    private fun saveSearchHistoryList(keyword: List<SearchHistoryEntity>) {
        App.prefs.saveSearchHistory(keyword)
    }

    override fun getSearchHistoryList(): List<SearchHistoryEntity> =
        App.prefs.getSearchHistoryList()

}