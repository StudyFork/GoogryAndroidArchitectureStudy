package com.example.model.data

interface MovieRepository {
    fun getMovieData(
        query: String,
        success: (NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}