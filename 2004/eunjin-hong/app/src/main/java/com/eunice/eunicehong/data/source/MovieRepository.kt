package com.eunice.eunicehong.data.source

import com.eunice.eunicehong.data.model.MovieContents
import com.eunice.eunicehong.data.source.local.MovieLocalDataSource
import com.eunice.eunicehong.data.source.remote.MovieRemoteDataSource

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) {

    fun saveMovieList(query: String, movieContents: MovieContents) {
        localDataSource.saveMovieList(query, movieContents)
    }

    fun removeMovieHistory() {
        localDataSource.removeMovieHistory()
    }

    fun deleteAllSearchRecentSuggestions() {
        localDataSource.deleteAllSearchRecentSuggestions()
    }

    fun getMovieList(
        query: String?,
        callback: MovieDataSource.LoadMoviesCallback
    ) {
        if (query.isNullOrBlank()) return

        localDataSource.saveSearchRecentSuggestions(query)

        try {
            val list = localDataSource.getMovieList(query)
            if (list.items.isEmpty()) {
                remoteDataSource.getMovieList(query, callback)
            } else {
                callback.onSuccess(query, list)
            }
        } catch (e: Throwable) {
            remoteDataSource.getMovieList(query, callback)
        }
    }
}