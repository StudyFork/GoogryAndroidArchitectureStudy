package com.example.googryandroidarchitecturestudy.data.remote

import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.network.MovieApi
import com.example.googryandroidarchitecturestudy.network.asDomainModel

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    override suspend fun searchMoviesFromRemote(search: String): List<Movie> {
        return MovieApi.movieService.searchMovies(search).asDomainModel()
    }
}