package com.example.studyfork.data.remote

import com.example.studyfork.data.Network
import com.example.studyfork.data.model.MovieSearchResponse
import io.reactivex.Single

class RemoteDataSourceImpl : RemoteDataSource {
    override fun searchMovie(query: String): Single<MovieSearchResponse> {
        return Network.naverApi.searchMovie(query)
    }
}