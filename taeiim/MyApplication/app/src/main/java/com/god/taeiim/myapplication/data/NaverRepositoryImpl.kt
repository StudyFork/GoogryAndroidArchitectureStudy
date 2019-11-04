package com.god.taeiim.myapplication.data

import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.source.NaverRemoteDataSourceImpl

object NaverRepositoryImpl : NaverRepository {
    override fun getResultData(
        searchType: String,
        query: String,
        success: (results: SearchResult) -> Unit,
        fail: (t: Throwable) -> Unit
    ) {
        NaverRemoteDataSourceImpl.getResultData(searchType, query, success, fail)
    }
}