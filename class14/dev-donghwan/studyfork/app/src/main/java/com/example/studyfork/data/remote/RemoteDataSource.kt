package com.example.studyfork.data.remote

import com.example.studyfork.data.model.MovieSearchResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun searchMovie(query: String): Single<MovieSearchResponse>
}