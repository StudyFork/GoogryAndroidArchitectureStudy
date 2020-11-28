package com.architecture.androidarchitecturestudy.data.local

import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity

interface MovieLocalDataSource {
    fun saveSearchHistory(query: String)
    fun getSearchHistoryList(): List<SearchHistoryEntity>
}