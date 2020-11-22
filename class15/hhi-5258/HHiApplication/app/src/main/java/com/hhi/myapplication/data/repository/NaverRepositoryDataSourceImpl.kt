package com.hhi.myapplication.data.repository

import com.hhi.myapplication.data.local.NaverLocalDataSourceImpl
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.remote.NaverRemoteDataSourceImpl

class NaverRepositoryDataSourceImpl : NaverRepositoryDataSource {
    private val naverRemoteDataSource = NaverRemoteDataSourceImpl()
    private val naverLocalDataSource = NaverLocalDataSourceImpl()
    override fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.searchMovies(query, success, failed)
    }

    override fun saveQuery(query: String) {
        naverLocalDataSource.saveQuery(query)
    }

    override fun getQueryList(): List<String> = naverLocalDataSource.getQueryList()
}