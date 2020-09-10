package com.example.aas.data.source.remote

import com.example.aas.data.model.ApiResult
import com.example.aas.data.network.RetrofitManager
import io.reactivex.Single

class RemoteDataSourceImpl : RemoteDataSource {
    override fun getMovies(query: String): Single<ApiResult> =
        RetrofitManager.naverMoviesApi.getMovies(query)
}