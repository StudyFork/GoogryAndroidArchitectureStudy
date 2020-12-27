package com.jay.data.data

import com.jay.data.model.Movie
import com.jay.data.model.SearchHistory
import com.jay.data.room.MovieDao
import com.jay.data.room.SearchHistoryDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieLocalDataSourceImpl @Inject constructor(
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

@InstallIn(ApplicationComponent::class)
@Module
abstract class MovieLocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMovieLocalDataSource(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource

}