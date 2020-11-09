package com.example.googryandroidarchitecturestudy.data.remote

import com.example.googryandroidarchitecturestudy.network.MovieApi
import com.example.googryandroidarchitecturestudy.network.NetworkMovie

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    override suspend fun searchMoviesFromRemote(search: String): NetworkMovie {
        return MovieApi.movieService.searchMovies(search)
    }
}