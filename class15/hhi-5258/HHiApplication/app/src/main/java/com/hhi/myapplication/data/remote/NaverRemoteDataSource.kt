package com.hhi.myapplication.data.remote

import com.hhi.myapplication.data.model.MovieData

interface NaverRemoteDataSource {
    fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    )
}