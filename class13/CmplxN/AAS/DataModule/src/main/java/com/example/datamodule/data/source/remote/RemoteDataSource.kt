package com.example.datamodule.data.source.remote

import com.example.datamodule.data.model.ApiResult
import io.reactivex.Single

interface RemoteDataSource {
    fun getMovies(query: String): Single<ApiResult>
}