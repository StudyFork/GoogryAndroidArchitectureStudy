package com.example.myapplication.data.repository

import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.source.NaverRemoteDataImpl

object NaverRepositoryImpl : NaverRepository {

    override fun getResultData(
        query: String,
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NaverRemoteDataImpl.getResultData(query, success, fail)
    }
}