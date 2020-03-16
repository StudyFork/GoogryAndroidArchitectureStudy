package com.example.myapplication.data.local.source

import com.example.myapplication.data.local.MovieEntity

interface MovieLocalDataSource {
    fun insertMovies(movies: List<MovieEntity>)
    fun getSearchMovies(title: String): List<MovieEntity>
}