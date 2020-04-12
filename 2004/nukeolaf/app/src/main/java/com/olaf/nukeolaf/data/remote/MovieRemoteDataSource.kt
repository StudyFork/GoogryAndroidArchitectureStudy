package com.olaf.nukeolaf.data.remote

import com.olaf.nukeolaf.MovieResponse

interface MovieRemoteDataSource {
    interface LoadMoviesCallback {
        fun onMoviesLoaded(movieResponse: MovieResponse)
        fun onResponseError(message: String)
        fun onFailure(t: Throwable)
    }

    fun getMovie(query: String, callback: LoadMoviesCallback)
}