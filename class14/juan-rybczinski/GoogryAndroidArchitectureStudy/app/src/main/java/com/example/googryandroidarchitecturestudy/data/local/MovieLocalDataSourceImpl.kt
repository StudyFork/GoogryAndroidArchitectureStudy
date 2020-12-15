package com.example.googryandroidarchitecturestudy.data.local

import com.example.googryandroidarchitecturestudy.database.DatabaseMovie
import com.example.googryandroidarchitecturestudy.database.DatabaseRecentSearch
import com.example.googryandroidarchitecturestudy.database.MovieDao
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalDataSource {
    override suspend fun searchMoviesFromLocal(search: String): List<DatabaseMovie> {
        return movieDao.searchMovies(search)
    }

    override suspend fun insertAll(movies: List<DatabaseMovie>) {
        movieDao.insertAll(movies)
    }

    override suspend fun searchRecentFromLocal(): List<DatabaseRecentSearch> =
        movieDao.searchRecentUpTo(LIMIT)

    override suspend fun insertRecentToLocal(recentSearch: DatabaseRecentSearch) {
        movieDao.insertRecentSearch(recentSearch)
    }

    companion object {
        private const val LIMIT = 5
    }
}
