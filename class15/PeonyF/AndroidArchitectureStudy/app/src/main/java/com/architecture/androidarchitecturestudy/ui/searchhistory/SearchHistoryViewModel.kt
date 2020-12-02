package com.architecture.androidarchitecturestudy.ui.searchhistory

import androidx.databinding.ObservableField
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl

class SearchHistoryViewModel(
    private val movieRepositoryImpl: MovieRepositoryImpl
) {
    val keywordList = ObservableField<List<SearchHistoryEntity>>()
    fun getRecentKeywordList() {
        keywordList.set(movieRepositoryImpl.getSearchHistoryList())
    }
}