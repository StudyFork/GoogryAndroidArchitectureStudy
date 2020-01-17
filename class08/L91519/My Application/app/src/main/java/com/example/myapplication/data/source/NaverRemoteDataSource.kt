package com.example.myapplication.data.source

import com.example.myapplication.data.model.MovieResult

interface NaverRemoteDataSource {

    fun getResultData(
        query: String,
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    )
}