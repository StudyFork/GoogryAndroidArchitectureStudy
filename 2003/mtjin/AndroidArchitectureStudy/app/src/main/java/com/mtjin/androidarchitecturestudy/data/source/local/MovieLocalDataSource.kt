package com.mtjin.androidarchitecturestudy.data.source.local

import com.mtjin.androidarchitecturestudy.data.Movie

interface MovieLocalDataSource {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
    suspend fun getSearchMovies(title : String) : List<Movie>
    suspend fun deleteAllMovies()
}