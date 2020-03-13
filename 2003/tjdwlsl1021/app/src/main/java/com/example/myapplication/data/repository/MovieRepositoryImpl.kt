package com.example.myapplication.data.repository

import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.remote.MovieRemoteDataSource
import com.example.myapplication.model.MovieEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieRepositoryImpl(private val mMovieRemoteDataSource: MovieRemoteDataSource, private val mMovieLocalDataSource: MovieLocalDataSource) :
    MovieRepository {
    override fun getMovieList(query: String, success: (List<MovieEntity>) -> Unit, failed: (Throwable) -> Unit) {
        runBlocking {
            launch {
                with(mMovieLocalDataSource.getSearchMovies(query)) {
                    if (this.isNotEmpty()) {
                        success(this)
                    }
                }
            }
        }
        mMovieRemoteDataSource.getMovieList(
            query,
            success = {
                runBlocking {
                    launch {
                        mMovieLocalDataSource.insertMovies(it)
                        success(it)
                    }
                }
            },
            failed = {
                runBlocking {
                    launch {
                        with(mMovieLocalDataSource.getSearchMovies(query)) {
                            if (this.isEmpty()) {
                                failed(it)
                            } else {
                                success(this)
                            }
                        }
                    }
                }
            }
        )
    }
}
