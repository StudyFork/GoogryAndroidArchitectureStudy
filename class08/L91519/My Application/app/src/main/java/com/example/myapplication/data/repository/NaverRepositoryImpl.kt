package com.example.myapplication.data.repository

import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.source.NaverRemoteDataImpl

object NaverRepositoryImpl : NaverRepository {

    override fun getResultData(
        query: String,
        success: (results: MovieResult) -> Unit,
        fail: (t: Throwable) -> Unit
    ) {
        NaverRemoteDataImpl.getResultData(query, success, fail)
    }

}