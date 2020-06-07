package com.kyudong.data.repository

import com.kyudong.data.local.MovieLocalDataSource
import com.kyudong.data.model.Movie
import com.kyudong.data.remote.MovieRemoteDataSource

internal class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {

    override fun getMovieListRemote(
        searchQuery: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieRemoteDataSource.getMovieList(
            query = searchQuery,
            success = { movieList: List<Movie> ->
                Thread {
                    saveMovieDataLocal(movieList)
                }.start()
                success(movieList)
            },
            failure = failure
        )
    }

    override fun getMovieListLocal(searchQuery: String): List<Movie> {
        return movieLocalDataSource.getMovieList(query = searchQuery)
    }

    override fun saveMovieDataLocal(movieList: List<Movie>) {
        movieLocalDataSource.saveMovieList(movieList)
    }
}