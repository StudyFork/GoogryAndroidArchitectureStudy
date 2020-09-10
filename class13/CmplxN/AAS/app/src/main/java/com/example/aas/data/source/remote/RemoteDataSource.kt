package com.example.aas.data.source.remote

import com.example.aas.data.model.ApiResult
import io.reactivex.Single

interface RemoteDataSource {
    fun getMovies(query: String): Single<ApiResult>
}