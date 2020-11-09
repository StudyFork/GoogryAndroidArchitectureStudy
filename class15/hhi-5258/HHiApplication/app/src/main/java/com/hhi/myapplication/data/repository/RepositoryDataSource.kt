package com.hhi.myapplication.data.repository

import com.hhi.myapplication.data.model.MovieData

interface RepositoryDataSource {
    fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    )
}