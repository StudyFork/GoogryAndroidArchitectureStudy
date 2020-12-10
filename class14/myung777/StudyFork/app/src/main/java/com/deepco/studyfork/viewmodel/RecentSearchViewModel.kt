package com.deepco.studyfork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepco.studyfork.data.local.LocalMovieDataImpl
import com.deepco.studyfork.data.model.RecentSearchData
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl
import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl

class RecentSearchViewModel : ViewModel() {
    private val _recentSearchTitle = MutableLiveData<String>()
    val recentSearchTitle: LiveData<String> = _recentSearchTitle
    private val _searchHistoryItems = MutableLiveData<List<RecentSearchData>>()
    val searchHistoryItems: LiveData<List<RecentSearchData>> = _searchHistoryItems
    private val repositoryMovieDataImpl = RepositoryMovieDataImpl(
        RemoteMovieDataImpl(),
        LocalMovieDataImpl()
    )

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