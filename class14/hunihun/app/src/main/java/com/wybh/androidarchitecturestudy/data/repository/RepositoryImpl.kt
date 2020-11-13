package com.wybh.androidarchitecturestudy.data.repository

import com.wybh.androidarchitecturestudy.data.remote.NaverRemoteDataSourceImpl
import com.wybh.androidarchitecturestudy.model.ResponseCinemaData

class RepositoryImpl(private val naverRemoteDataSource: NaverRemoteDataSourceImpl) : Repository {

    override fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.searchCinema(query, success, fail)
    }
}