package com.example.data2.soruce.remote

import com.example.data2.model.Movie

interface MovieRemoteDataSource {
    fun getMovieList(
        query: String,
        success: (List<Movie>) -> Unit,
        failed: (Throwable) -> Unit
    )
}