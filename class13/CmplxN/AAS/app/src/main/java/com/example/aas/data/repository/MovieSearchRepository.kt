package com.example.aas.data.repository

import android.content.Context
import com.example.aas.data.model.ApiResult
import io.reactivex.Single

interface MovieSearchRepository {

    fun getMovies(query: String, context: Context): Single<ApiResult>

    fun getSavedQueries(context: Context): List<String>
}