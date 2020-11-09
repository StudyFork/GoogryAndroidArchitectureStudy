package com.example.googryandroidarchitecturestudy.data.local

import com.example.googryandroidarchitecturestudy.database.DatabaseMovie
import com.example.googryandroidarchitecturestudy.database.MovieDatabase

class MovieLocalDataSourceImpl(
    private val database: MovieDatabase
) : MovieLocalDataSource {
    override suspend fun searchMovies(search: String): List<DatabaseMovie> {
        return database.movieDao.searchMovies(search)
    }

    override suspend fun insertAll(movies: List<DatabaseMovie>) {
        database.movieDao.insertAll(movies)
    }
}