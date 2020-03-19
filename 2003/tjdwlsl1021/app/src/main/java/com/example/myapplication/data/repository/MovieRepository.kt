package com.example.myapplication.data.repository

import com.example.myapplication.data.local.MovieEntity

interface MovieRepository {
    fun getMovieList(
        query: String,
        success: (List<MovieEntity>) -> Unit,
        failed: (Throwable) -> Unit
    )
}
