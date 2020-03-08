package io.github.sooakim.data.remote

import io.github.sooakim.data.remote.api.SAAuthApi
import io.github.sooakim.data.remote.api.SANaverMovieApi

interface SANetworkService {
    val movieApi: SANaverMovieApi
    val authApi: SAAuthApi
}