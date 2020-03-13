package com.example.myapplication.data.local.source

import com.example.myapplication.model.MovieEntity

interface MovieLocalDataSource {

    suspend fun insertMovies(movies: List<MovieEntity>)
    suspend fun getSearchMovies(title: String): List<MovieEntity>
}