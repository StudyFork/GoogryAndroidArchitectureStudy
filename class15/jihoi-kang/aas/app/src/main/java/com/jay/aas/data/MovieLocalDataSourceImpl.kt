package com.jay.aas.data

import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory
import com.jay.aas.room.RoomHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieLocalDataSourceImpl @Inject constructor(
    private val roomHelper: RoomHelper,
) : MovieLocalDataSource {

    override fun insertMovies(query: String, movies: List<Movie>) {
        roomHelper.movieDao.insertMovies(movies)
        roomHelper.searchHistoryDao.insertSearchHistory(SearchHistory(queryText = query))
    }

    override fun getMovies(): List<Movie> = roomHelper.movieDao.getMovies()

    override fun clearMovies() {
        roomHelper.movieDao.clearMovies()
    }

    override fun getSearchHistories(): List<SearchHistory> =
        roomHelper.searchHistoryDao.getSearchHistories()

}

@InstallIn(ApplicationComponent::class)
@Module
abstract class MovieLocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMovieLocalDataSource(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource

}