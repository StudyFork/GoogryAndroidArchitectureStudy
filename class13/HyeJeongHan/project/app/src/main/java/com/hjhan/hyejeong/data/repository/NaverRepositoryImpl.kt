package com.hjhan.hyejeong.data.repository

import com.hjhan.hyejeong.data.model.MovieData
import com.hjhan.hyejeong.data.source.local.NaverLocalDataSource
import com.hjhan.hyejeong.data.source.remote.NaverRemoteDataSource

class NaverRepositoryImpl(
    private val naverRemoteDataSourceImpl: NaverRemoteDataSource,
    private val naverLocalDataSourceImpl: NaverLocalDataSource
) : NaverRepository {

    override fun getSearchMovies(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {
        saveQuery(query)
        naverRemoteDataSourceImpl.getSearchMovies(query, success = success, failed = failed)
    }

    override fun saveQuery(query: String) {
        naverLocalDataSourceImpl.saveQuery(query)
    }

    override fun getQueryList(): List<String> {
        return naverLocalDataSourceImpl.getQueryList()
    }
}