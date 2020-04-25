package com.example.kyudong3.data.repository

import com.example.kyudong3.data.model.Movie

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