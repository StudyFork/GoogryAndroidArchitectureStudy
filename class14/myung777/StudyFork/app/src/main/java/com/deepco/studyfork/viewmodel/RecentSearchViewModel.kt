package com.deepco.studyfork.viewmodel

import androidx.databinding.ObservableField
import com.deepco.studyfork.data.local.LocalMovieDataImpl
import com.deepco.studyfork.data.model.RecentSearchData
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl
import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl

class RecentSearchViewModel {
    val recentSearchTitle = ObservableField<String>()
    val searchHistoryItems = ObservableField<List<RecentSearchData>>()

    private val repositoryMovieDataImpl = RepositoryMovieDataImpl(
        RemoteMovieDataImpl(),
        LocalMovieDataImpl()
    )

    fun getSearchHistories() {
        val searchHistories = repositoryMovieDataImpl.getQueryList()
        searchHistoryItems.set(searchHistories)
    }

    fun setRecentSearchTitle(title: String) {
        recentSearchTitle.set(title)
    }

}