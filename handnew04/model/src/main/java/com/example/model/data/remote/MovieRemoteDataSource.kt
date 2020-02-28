package com.example.model.data.remote

import com.example.model.data.NaverMovieResponse


internal interface MovieRemoteDataSource {
    fun getMovieData(
        query: String,
        success: (NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}