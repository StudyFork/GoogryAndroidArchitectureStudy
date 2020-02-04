package com.example.myapplication.data.repository

import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.source.NaverLocalDataSourceImpl
import com.example.myapplication.data.source.NaverRemoteDataSourceImpl

object NaverRepositoryImpl : NaverRepository {

    override fun getResultData(
        query: String,
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NaverRemoteDataSourceImpl.getResultData(query, success, fail)
    }

    override fun getResentData(
        success: (results: MovieResult) -> Unit,
        fail: (t: Throwable) -> Unit
    ) {
        NaverLocalDataSourceImpl.getResentData(success, fail)
    }

    override fun saveCache(success: () -> Unit, fail: (t: Throwable) -> Unit) {
        NaverLocalDataSourceImpl.saveCache()
    }

}