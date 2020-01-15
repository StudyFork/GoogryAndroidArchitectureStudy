package com.example.study.data.datasource.remote

import com.example.study.data.model.NaverSearchResponse

interface NaverSearchRemoteDataSource {
    fun getMovies(query: String, success: (NaverSearchResponse) -> Unit, fail: (Throwable) -> Unit)
}