package com.example.kyudong3.data.repository

import com.example.kyudong3.data.local.MovieLocalDataSource
import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
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