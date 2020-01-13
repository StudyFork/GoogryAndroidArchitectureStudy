package com.example.myapplication.data.source

import com.example.myapplication.data.model.MovieResult

interface NaverRemoteData {

    fun getResultData(
        query: String,
        success: (results: MovieResult) -> Unit,
        fail: (t: Throwable) -> Unit
    )
}