package com.example.hw2_project.data.repository

import com.example.hw2_project.Movie
import com.example.hw2_project.MovieList

interface MovieRepository {

    fun getMovieList(
        query : String,
        success : (MovieList) -> Unit,
        fail : (Throwable) -> Unit
    )
}