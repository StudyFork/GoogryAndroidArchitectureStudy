package com.example.data.source

import com.example.data.model.network.NetworkMovie

interface MovieRemoteDataSource {
    suspend fun searchMoviesFromRemote(search: String): NetworkMovie
}