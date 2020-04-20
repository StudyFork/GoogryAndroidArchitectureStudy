package com.example.remote.source

import com.example.remote.model.Movies
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getMovies(query: String): Single<Movies>
}