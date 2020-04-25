package com.eunice.eunicehong.data.source

import com.eunice.eunicehong.data.model.MovieList

interface MovieDataSource {
    interface LoadMoviesCallback {
        fun onSuccess(movieList: MovieList)
        fun onFailure(e: Throwable)
    }

    fun getMovieList(query: String, callback: LoadMoviesCallback)
}