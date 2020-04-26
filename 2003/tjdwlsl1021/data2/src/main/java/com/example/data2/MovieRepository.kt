package com.example.data2

import com.example.data2.model.Movie

interface MovieRepository {
    fun getMovieList(
        query: String,
        success: (List<Movie>) -> Unit,
        failed: (Throwable) -> Unit
    )
}
