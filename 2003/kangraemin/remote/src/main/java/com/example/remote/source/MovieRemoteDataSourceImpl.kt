package com.example.remote.source

import com.example.remote.model.Movies
import io.reactivex.Single

class MovieRemoteDataSourceImpl(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override fun getMovies(query: String): Single<Movies> {
        return movieApi.getSearchItems(query = query)
    }
}