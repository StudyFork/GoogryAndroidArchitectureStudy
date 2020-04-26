package com.example.data2

import com.example.data2.model.Movie
import com.example.data2.soruce.local.MovieLocalDataSource
import com.example.data2.soruce.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) :
    MovieRepository {
    override fun getMovieList(
        query: String,
        success: (List<Movie>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        with(movieLocalDataSource.getSearchMovies(query)) {
            if (this.isNotEmpty()) {
                success(this)
            }
        }

        movieRemoteDataSource.getMovieList(
            query,
            success = {
                movieLocalDataSource.insertMovies(it)
                success(it)
            },

            failed = {
                with(movieLocalDataSource.getSearchMovies(query)) {
                    if (this.isEmpty()) {
                        failed(it)
                    } else {
                        success(this)
                    }
                }
            }
        )
    }
}
