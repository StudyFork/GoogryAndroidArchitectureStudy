package com.jay.aas.data

import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory
import com.jay.aas.room.MovieDao
import com.jay.aas.room.SearchHistoryDao

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val searchHistoryDao: SearchHistoryDao,
) : MovieLocalDataSource {

    override fun insertMovies(query: String, movies: List<Movie>) {
        movieDao.insertMovies(movies)
        searchHistoryDao.insertSearchHistory(SearchHistory(queryText = query))
    }

    override fun getMovies(): List<Movie> = movieDao.getMovies()

    override fun clearMovies() {
        movieDao.clearMovies()
    }

    override fun getSearchHistories(): List<SearchHistory> =
        searchHistoryDao.getSearchHistories()

}