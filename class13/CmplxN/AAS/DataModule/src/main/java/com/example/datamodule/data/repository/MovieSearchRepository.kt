package com.example.datamodule.data.repository

import com.example.datamodule.data.model.ApiResult
import io.reactivex.Single

interface MovieSearchRepository {

    fun getMovies(query: String): Single<ApiResult>

    fun getSavedQueries(): Single<List<String>>

    fun onDestroy()
}