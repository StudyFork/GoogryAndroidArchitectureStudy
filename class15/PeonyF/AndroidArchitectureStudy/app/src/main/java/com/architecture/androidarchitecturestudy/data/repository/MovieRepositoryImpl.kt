package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
    private val movieLocalDataSourceImpl: MovieLocalDataSourceImpl
) : MovieRepository {
    override fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        return movieRemoteDataSource.getMovieData(
            keyword = keyword,
            display = display,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun saveSearchHistory(keyword: String) {
        movieLocalDataSourceImpl.saveSearchHistory(keyword)
    }

    override fun getSearchHistoryList(): List<SearchHistoryEntity> {
        return movieLocalDataSourceImpl.getSearchHistoryList()
    }
}

