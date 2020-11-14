package com.hhi.myapplication.data.repository

import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.remote.NaverRemoteDataSourceImpl

class NaverRepositoryDataSourceImpl : NaverRepositoryDataSource {
    private val naverRemoteDataSource = NaverRemoteDataSourceImpl()
    override fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.searchMovies(query, success, failed)
    }
}