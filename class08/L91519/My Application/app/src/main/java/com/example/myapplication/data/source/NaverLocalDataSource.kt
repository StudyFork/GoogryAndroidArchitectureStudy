package com.example.myapplication.data.source

import com.example.myapplication.data.model.MovieResult

interface NaverLocalDataSource {
    fun getResentData(
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun saveCache()
}