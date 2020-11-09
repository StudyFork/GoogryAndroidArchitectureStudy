package com.example.googryandroidarchitecturestudy.data.repository

import com.example.googryandroidarchitecturestudy.data.local.MovieLocalDataSource
import com.example.googryandroidarchitecturestudy.data.remote.MovieRemoteDataSource
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.network.asDomainModel

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) {
    suspend fun searchMovies(search: String): List<Movie> {
        return remoteDataSource.searchMoviesFromRemote(search).asDomainModel()
    }
}