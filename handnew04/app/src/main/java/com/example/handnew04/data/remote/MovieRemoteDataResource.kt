package com.example.handnew04.data.remote

import com.example.handnew04.data.NaverMovieResponse

interface MovieRemoteDataResource {
    fun getMovieData(
        query: String,
        success: (NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}