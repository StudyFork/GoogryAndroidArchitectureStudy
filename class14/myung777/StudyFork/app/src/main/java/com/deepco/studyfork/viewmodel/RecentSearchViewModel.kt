package com.deepco.studyfork.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepco.data.data.model.RecentSearchData
import com.deepco.data.data.repository.RepositoryMovieDataImpl

class RecentSearchViewModel @ViewModelInject constructor(
    private val repositoryMovieDataImpl: RepositoryMovieDataImpl
) : ViewModel() {
    private val _recentSearchTitle = MutableLiveData<String>()
    val recentSearchTitle: LiveData<String> = _recentSearchTitle
    private val _searchHistoryItems = MutableLiveData<List<RecentSearchData>>()
    val searchHistoryItems: LiveData<List<RecentSearchData>> = _searchHistoryItems

    init {
        getSearchHistories()
    }

    private fun getSearchHistories() {
        val searchHistories = repositoryMovieDataImpl.getQueryList()
        _searchHistoryItems.value = searchHistories
    }

    fun setRecentSearchTitle(title: String) {
        _recentSearchTitle.value = title
    }

}