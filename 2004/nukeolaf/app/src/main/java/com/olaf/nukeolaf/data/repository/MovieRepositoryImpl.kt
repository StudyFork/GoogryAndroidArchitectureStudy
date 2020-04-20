package com.olaf.nukeolaf.data.repository

import com.olaf.nukeolaf.data.local.MovieLocalDataSource
import com.olaf.nukeolaf.data.model.MovieResponse
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getMovies(): MovieResponse? {
        return movieLocalDataSource.getMovies()
    }

    override fun searchMovies(query: String, callback: MovieRepository.LoadMoviesCallback) {
        movieRemoteDataSource.getMovies(query, object : MovieRemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movieResponse: MovieResponse) {
                callback.onMoviesLoaded(movieResponse)
                movieLocalDataSource.saveMovies(movieResponse)
            }

            override fun onResponseError(message: String) {
                callback.onResponseError(message)
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}