package com.olaf.nukeolaf.data.repository

import com.olaf.nukeolaf.data.model.MovieResponse

interface MovieRepository {

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movieResponse: MovieResponse)
        fun onResponseError(message: String)
        fun onFailure(t: Throwable)
    }

    fun getMovies(): MovieResponse?
    fun searchMovies(query: String, callback: LoadMoviesCallback)

}