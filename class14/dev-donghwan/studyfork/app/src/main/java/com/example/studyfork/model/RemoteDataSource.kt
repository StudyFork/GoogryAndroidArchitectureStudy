package com.example.studyfork.model

import io.reactivex.Single

interface RemoteDataSource {
    fun searchMovie(query: String): Single<MovieSearchResponse>
}