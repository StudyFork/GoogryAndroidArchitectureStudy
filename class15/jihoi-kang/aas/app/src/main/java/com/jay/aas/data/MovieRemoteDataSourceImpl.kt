package com.jay.aas.data

import com.jay.aas.api.MovieService
import com.jay.aas.model.Movie

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService,
) : MovieRemoteDataSource {

    override suspend fun getMovies(
        query: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        try {
            val response = movieService.getMovies(query)
            val movies = response.items
            success(movies)
        } catch (e: Exception) {
            e.printStackTrace()
            failure(e)
        }
    }
}