package com.hhi.myapplication.data.repository

import com.hhi.myapplication.data.model.MovieData

interface NaverRepositoryDataSource {
    fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    )

    fun saveQuery(query: String)

    fun getQueryList(): List<String>
}