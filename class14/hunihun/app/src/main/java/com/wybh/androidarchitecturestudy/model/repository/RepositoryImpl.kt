package com.wybh.androidarchitecturestudy.model.repository

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl

class RepositoryImpl(private val naverRemoteDataSource: NaverRemoteDataSourceImpl) : Repository {

    override fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.searchCinema(query, success, fail)
    }

    fun removeCompositeDisposable() {
        naverRemoteDataSource.removeCompositeDisposable()
    }
}