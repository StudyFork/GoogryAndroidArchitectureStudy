package com.example.myapplication.data.remote

import com.example.myapplication.model.MovieEntity

interface MovieRemoteDataSource {
    fun getMovieList(query: String, success: (List<MovieEntity>) -> Unit, failed: (Throwable) -> Unit)
}