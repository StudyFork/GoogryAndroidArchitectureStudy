package com.example.study.data.repository

import com.example.study.data.datasource.remote.NaverSearchRemoteDataSource
import com.example.study.data.datasource.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.data.model.NaverSearchResponse

class NaverSearchRepositoryImpl : NaverSearchRepository {

    private val naverSearchRemoteDatasource: NaverSearchRemoteDataSource =
        NaverSearchRemoteDataSourceImpl()

    override fun getMovies(
        query: String,
        success: (NaverSearchResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDatasource.getMovies(query, success, fail)
    }
}