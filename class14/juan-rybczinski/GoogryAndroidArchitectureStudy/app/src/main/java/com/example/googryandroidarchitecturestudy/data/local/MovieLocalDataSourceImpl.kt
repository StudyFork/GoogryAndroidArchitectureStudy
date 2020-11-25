package com.example.googryandroidarchitecturestudy.data.local

import com.example.googryandroidarchitecturestudy.database.DatabaseMovie
import com.example.googryandroidarchitecturestudy.database.DatabaseRecentSearch
import com.example.googryandroidarchitecturestudy.database.MovieDatabase

class MovieLocalDataSourceImpl(
    private val database: MovieDatabase
) : MovieLocalDataSource {
    override suspend fun searchMoviesFromLocal(search: String): List<DatabaseMovie> {
        return database.movieDao.searchMovies(search)
    }

    override suspend fun insertAll(movies: List<DatabaseMovie>) {
        database.movieDao.insertAll(movies)
    }

    override suspend fun searchRecentFromLocal(): List<DatabaseRecentSearch> =
        database.movieDao.searchRecentUpTo(LIMIT)

    override suspend fun insertRecentToLocal(recentSearch: DatabaseRecentSearch) {
        database.movieDao.insertRecentSearch(recentSearch)
    }

    companion object {
        private const val LIMIT = 5
    }
}
