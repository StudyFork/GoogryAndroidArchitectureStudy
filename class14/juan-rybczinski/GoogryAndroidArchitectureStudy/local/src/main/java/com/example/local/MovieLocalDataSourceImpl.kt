package com.example.local

import com.example.data.model.database.DatabaseMovie
import com.example.data.model.database.DatabaseRecentSearch
import com.example.local.database.MovieDao
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : com.example.data.source.MovieLocalDataSource {
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
