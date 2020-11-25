package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.ui.contract.RecentContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class RecentPresenter(
    private val view: RecentContract.View,
    private val repository: MovieRepository
) : BasePresenter(view), RecentContract.Presenter {
    override suspend fun getRecentSearch() = repository.searchRecent()

}

