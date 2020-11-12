package com.jay.aas.data

import com.jay.aas.api.MovieService

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService,
) : MovieRemoteDataSource {

    override suspend fun getSearchMovies(query: String) =
        movieService.getMovies(query).items

}