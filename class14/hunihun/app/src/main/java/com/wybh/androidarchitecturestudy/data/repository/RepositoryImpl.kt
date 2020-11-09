package com.wybh.androidarchitecturestudy.data.repository

import com.wybh.androidarchitecturestudy.data.remote.RemoteNaverApiImpl
import com.wybh.androidarchitecturestudy.model.ResponseCinemaData

class RepositoryImpl(private val remoteNaverApi: RemoteNaverApiImpl) : Repository {

    override fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        remoteNaverApi.searchCinema(query, success, fail)
    }
}