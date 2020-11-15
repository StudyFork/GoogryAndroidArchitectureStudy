package com.example.hw2_project.data.remote

import com.example.hw2_project.data.MovieList

interface MovieRemoteDataSource {
    fun getMovieFromRemote(
        query : String,
        success : (MovieList) -> Unit,
        fail : (Throwable) -> Unit
    )
}