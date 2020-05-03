package com.example.data2.soruce.local

import com.example.data2.model.Movie

interface MovieLocalDataSource {
    fun insertMovies(movies: List<Movie>)
    fun getSearchMovies(title: String): List<Movie>
}