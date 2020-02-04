package com.example.myapplication.data.repository

import com.example.myapplication.data.model.MovieResult

interface NaverRepository {

    fun getResultData(
        query: String,
        success: (results: MovieResult) -> Unit,
        fail: (t: Throwable) -> Unit
    )

    fun getResentData(
        success: (results: MovieResult) -> Unit,
        fail: (t: Throwable) -> Unit
    )

    fun saveCache(
        success: () -> Unit,
        fail: (t: Throwable) -> Unit
    )
}