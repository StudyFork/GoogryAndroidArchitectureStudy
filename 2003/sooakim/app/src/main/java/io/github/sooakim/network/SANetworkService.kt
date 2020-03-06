package io.github.sooakim.network

import io.github.sooakim.network.api.SAAuthApi
import io.github.sooakim.network.api.SANaverMovieApi

interface SANetworkService {
    val movieApi: SANaverMovieApi
    val authApi: SAAuthApi
}