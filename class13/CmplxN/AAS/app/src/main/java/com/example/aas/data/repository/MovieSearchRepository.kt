package com.example.aas.data.repository

import com.example.aas.data.model.ApiResult
import io.reactivex.Single

interface MovieSearchRepository {

    fun getMovies(query: String): Single<ApiResult>

    fun getSavedQueries(): List<String>
}