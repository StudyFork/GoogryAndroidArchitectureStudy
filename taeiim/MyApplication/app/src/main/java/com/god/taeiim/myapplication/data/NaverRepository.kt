package com.god.taeiim.myapplication.data

import com.god.taeiim.myapplication.api.model.SearchResult

interface NaverRepository {
    fun getResultData(
        searchType: String,
        query: String,
        success: (results: SearchResult) -> Unit,
        fail: (t: Throwable) -> Unit
    )
}