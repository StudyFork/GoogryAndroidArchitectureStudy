package com.example.kyudong3.data.repository

import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.remote.MovieRemoteDataSource
import com.example.kyudong3.data.remote.MovieRemoteDataSourceImpl

class MovieRepositoryImpl : MovieRepository {
    private val movieDataSourceImpl: MovieRemoteDataSource by lazy {
        MovieRemoteDataSourceImpl()
    }

    override fun getSearchMovie(
        searchQuery: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieDataSourceImpl.getMovieList(query = searchQuery, success = success, failure = failure)
    }
}