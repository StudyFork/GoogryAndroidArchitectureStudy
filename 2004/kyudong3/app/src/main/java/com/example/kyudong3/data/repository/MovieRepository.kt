package com.example.kyudong3.data.repository

import com.example.kyudong3.data.model.Movie

interface MovieRepository {
    fun getSearchMovie(
        searchQuery: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    )
}