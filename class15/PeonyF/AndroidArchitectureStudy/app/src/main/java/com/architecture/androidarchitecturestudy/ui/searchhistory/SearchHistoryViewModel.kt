package com.architecture.androidarchitecturestudy.ui.searchhistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.data.repository.MovieRepository

class SearchHistoryViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val keywordList = MutableLiveData<List<SearchHistoryEntity>>()
    fun getRecentKeywordList() {
        keywordList.set(movieRepository.getSearchHistoryList())
    }
}