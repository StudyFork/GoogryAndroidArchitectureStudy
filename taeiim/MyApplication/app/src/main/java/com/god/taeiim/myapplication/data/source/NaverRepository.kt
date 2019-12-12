package com.god.taeiim.myapplication.data.source

import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.SearchHistory

interface NaverRepository {

    fun getResultData(
        searchType: String,
        query: String,
        success: (results: SearchResult) -> Unit,
        fail: (t: Throwable) -> Unit
    )

    fun getLastSearchResultData(searchType: String): SearchHistory

    fun saveSearchResult(searchHistory: SearchHistory)
}