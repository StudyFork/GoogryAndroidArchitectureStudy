package com.example.myapplication.data.remote.source

import com.example.myapplication.data.local.MovieEntity

interface MovieRemoteDataSource {
    fun getMovieList(
        query: String,
        success: (List<MovieEntity>) -> Unit,
        failed: (Throwable) -> Unit
    )
}