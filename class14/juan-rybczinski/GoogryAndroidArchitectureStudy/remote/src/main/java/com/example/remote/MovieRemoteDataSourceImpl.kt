package com.example.remote

import com.example.data.model.network.NetworkMovie
import com.example.remote.network.MovieApiService
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieApiService
) : com.example.data.source.MovieRemoteDataSource {
    override suspend fun searchMoviesFromRemote(search: String): NetworkMovie {
        return movieService.searchMovies(search)
    }
}