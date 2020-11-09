package com.example.googryandroidarchitecturestudy.data.remote

import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.network.NetworkMovie

interface MovieRemoteDataSource {
    suspend fun searchMoviesFromRemote(search: String): NetworkMovie
}