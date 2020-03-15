package com.example.myapplication.data.repository

import com.example.myapplication.model.MovieEntity

interface MovieRepository {
    fun getMovieList(query: String, success: (List<MovieEntity>) -> Unit, failed: (Throwable) -> Unit)
}
