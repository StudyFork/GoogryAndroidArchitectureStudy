package com.example.study.data.repository

import com.example.study.data.datasource.remote.NaverSearchRemoteDatasource
import com.example.study.data.datasource.remote.NaverSearchRemoteDatasourceImpl
import com.example.study.data.model.NaverSearchResponse

class NaverSearchRepositoryImpl : NaverSearchRepository {

    private val naverSearchRemoteDatasource: NaverSearchRemoteDatasource = NaverSearchRemoteDatasourceImpl()

    override fun getMovies(
        query: String,
        success: (NaverSearchResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDatasource.getMovies(query, success, fail)
    }
}