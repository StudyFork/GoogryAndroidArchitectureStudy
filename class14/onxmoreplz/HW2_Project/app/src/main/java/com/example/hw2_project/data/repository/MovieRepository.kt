package com.example.hw2_project.data.repository

import com.example.hw2_project.data.MovieList

interface MovieRepository {

    fun getMovieList(
        query : String,
        success : (MovieList) -> Unit,
        fail : (Throwable) -> Unit
    )
}