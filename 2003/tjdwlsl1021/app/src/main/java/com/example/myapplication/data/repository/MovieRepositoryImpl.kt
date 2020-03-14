package com.example.myapplication.data.repository

import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.remote.MovieRemoteDataSource
import com.example.myapplication.model.MovieEntity

class MovieRepositoryImpl(private val mMovieRemoteDataSource: MovieRemoteDataSource, private val mMovieLocalDataSource: MovieLocalDataSource) :
    MovieRepository {
    override fun getMovieList(query: String, success: (List<MovieEntity>) -> Unit, failed: (Throwable) -> Unit) {
        with(mMovieLocalDataSource.getSearchMovies(query)) {
            if (this.isNotEmpty()) {
                success(this)
            }
        }

        mMovieRemoteDataSource.getMovieList(
            query,
            success = {
                mMovieLocalDataSource.insertMovies(it)
                success(it)
            },

            failed = {
                with(mMovieLocalDataSource.getSearchMovies(query)) {
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
