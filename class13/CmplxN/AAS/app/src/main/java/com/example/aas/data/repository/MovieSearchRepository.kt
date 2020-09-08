package com.example.aas.data.repository

import com.example.aas.data.model.ApiResult
import io.reactivex.Completable
import io.reactivex.Single

interface MovieSearchRepository {

    fun getMovies(query: String): Single<Pair<ApiResult, Completable>>

    fun getSavedQueries(): Single<List<String>>
}