package com.example.kyudong3.data.repository

import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.remote.MovieDataSourceImpl

class MovieRepositoryImpl : MovieRepository {
    private val movieDataSourceImpl: MovieDataSourceImpl by lazy {
        MovieDataSourceImpl()
    }

    override fun getSearchMovie(
        searchQuery: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieDataSourceImpl.getMovieList(query = searchQuery, success = success, failure = failure)
    }
}