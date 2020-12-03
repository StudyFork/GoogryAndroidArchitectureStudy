package com.architecture.androidarchitecturestudy.ui.searchhistory

import androidx.databinding.ObservableField
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.data.repository.MovieRepository

class SearchHistoryViewModel(
    private val movieRepository: MovieRepository
) {
    val keywordList = ObservableField<List<SearchHistoryEntity>>()
    fun getRecentKeywordList() {
        keywordList.set(movieRepository.getSearchHistoryList())
    }
}