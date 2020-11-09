package com.example.studyfork.model

import io.reactivex.Single

class RemoteDataSourceImpl : RemoteDataSource {
    override fun searchMovie(query: String): Single<MovieSearchResponse> {
        return Network.naverApi.searchMovie(query)
    }
}