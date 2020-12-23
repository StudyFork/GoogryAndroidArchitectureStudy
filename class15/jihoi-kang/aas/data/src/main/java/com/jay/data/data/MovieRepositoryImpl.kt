package com.jay.data.data

import com.jay.data.model.Movie
import com.jay.data.model.SearchHistory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
) : MovieRepository {

    private val TAG = this::class.java.simpleName

    override suspend fun getSearchMovies(query: String): List<Movie> {
        val movies = movieRemoteDataSource.getSearchMovies(query)
        movieLocalDataSource.insertMovies(query, movies)

        return movies
    }

    override suspend fun getMovies(): List<Movie> =
        movieLocalDataSource.getMovies()

    override fun getSearchHistories(): List<SearchHistory> =
        movieLocalDataSource.getSearchHistories()

}

@InstallIn(ApplicationComponent::class)
@Module
abstract class MovieRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}