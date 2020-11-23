package com.example.hw2_project.data.remote

import com.example.hw2_project.data.api.NaverMovieData

interface MovieRemoteDataSource {
    fun getMovieFromRemote(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}