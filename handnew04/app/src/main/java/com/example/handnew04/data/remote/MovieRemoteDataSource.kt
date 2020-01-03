package com.example.handnew04.data.remote

import com.example.handnew04.data.NaverMovieResponse

interface MovieRemoteDataSource {
    fun getMovieData(
        query: String,
        success: (NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}