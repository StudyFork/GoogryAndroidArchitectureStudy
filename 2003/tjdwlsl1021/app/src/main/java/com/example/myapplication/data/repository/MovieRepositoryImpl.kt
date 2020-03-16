package com.example.myapplication.data.repository

import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.remote.source.MovieRemoteDataSource
import com.example.myapplication.data.local.MovieEntity

class MovieRepositoryImpl(private val movieRemoteDataSource: MovieRemoteDataSource, private val movieLocalDataSource: MovieLocalDataSource) :
    MovieRepository {
    override fun getMovieList(query: String, success: (List<MovieEntity>) -> Unit, failed: (Throwable) -> Unit) {
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
