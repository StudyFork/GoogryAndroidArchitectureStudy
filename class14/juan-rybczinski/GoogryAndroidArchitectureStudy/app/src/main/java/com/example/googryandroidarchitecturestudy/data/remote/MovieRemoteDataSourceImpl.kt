package com.example.googryandroidarchitecturestudy.data.remote

import com.example.googryandroidarchitecturestudy.network.MovieApiService
import com.example.googryandroidarchitecturestudy.network.NetworkMovie
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieApiService
) : MovieRemoteDataSource {
    override suspend fun searchMoviesFromRemote(search: String): NetworkMovie {
        return movieService.searchMovies(search)
    }
}