package com.example.study.data.source.local

import com.example.study.data.source.local.model.SearchResult

interface NaverSearchLocalDataSource {

    fun addSearchResult(searchResult: SearchResult)

    fun getRecentSearchResult(): SearchResult
}