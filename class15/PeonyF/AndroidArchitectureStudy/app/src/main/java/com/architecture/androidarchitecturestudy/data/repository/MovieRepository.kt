package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity

interface MovieRepository {
    fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit,
    )

    fun saveSearchHistory(keyword: String)
    fun getSearchHistoryList(): List<SearchHistoryEntity>
}