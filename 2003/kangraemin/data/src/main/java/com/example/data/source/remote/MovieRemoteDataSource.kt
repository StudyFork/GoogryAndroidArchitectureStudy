package com.example.data.source.remote

import com.example.data.model.Movie
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getMovies(query: String): Single<List<Movie>>
}