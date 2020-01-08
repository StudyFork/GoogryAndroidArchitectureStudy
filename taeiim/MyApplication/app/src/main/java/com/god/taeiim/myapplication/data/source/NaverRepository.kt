package com.god.taeiim.myapplication.data.source

import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.SearchHistory

interface NaverRepository {

    fun getResultData(
        searchType: Tabs,
        query: String,
        success: (results: SearchResult) -> Unit,
        fail: (t: Throwable) -> Unit
    )

    fun getLastSearchResultData(searchType: Tabs): SearchHistory

    fun saveSearchResult(searchHistory: SearchHistory)
}