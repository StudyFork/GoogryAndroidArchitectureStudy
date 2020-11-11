package com.wybh.androidarchitecturestudy.data.repository

import com.wybh.androidarchitecturestudy.data.remote.RemoteNaverDataSourceImpl
import com.wybh.androidarchitecturestudy.model.ResponseCinemaData

class RepositoryImpl(private val remoteNaverDataSource: RemoteNaverDataSourceImpl) : Repository {

    override fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        remoteNaverDataSource.searchCinema(query, success, fail)
    }
}