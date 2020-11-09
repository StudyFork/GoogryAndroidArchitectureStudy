package com.example.googryandroidarchitecturestudy.data.remote

import com.example.googryandroidarchitecturestudy.domain.Movie

interface MovieRemoteDataSource {
    suspend fun searchMoviesFromRemote(search: String): List<Movie>
}