package com.olaf.nukeolaf.data.remote

import com.olaf.nukeolaf.data.model.MovieResponse

interface MovieRemoteDataSource {
    interface LoadMoviesCallback {
        fun onMoviesLoaded(movieResponse: MovieResponse)
        fun onResponseError(message: String)
        fun onFailure(t: Throwable)
    }

    fun getMovies(query: String, callback: LoadMoviesCallback)
}