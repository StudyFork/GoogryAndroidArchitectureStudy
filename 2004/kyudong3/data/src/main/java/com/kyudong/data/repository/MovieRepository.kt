package com.kyudong.data.repository

import com.kyudong.data.model.Movie

interface MovieRepository {
    fun getMovieListRemote(
        searchQuery: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    )

    fun getMovieListLocal(
        searchQuery: String
    ): List<Movie>

    fun saveMovieDataLocal(movieList: List<Movie>)
}