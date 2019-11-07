package com.god.taeiim.myapplication.data.source

import com.god.taeiim.myapplication.api.model.SearchResult

interface NaverDataSource {
    fun getResultData(
        searchType: String,
        query: String,
        success: (results: SearchResult) -> Unit,
        fail: (t: Throwable) -> Unit
    )
}