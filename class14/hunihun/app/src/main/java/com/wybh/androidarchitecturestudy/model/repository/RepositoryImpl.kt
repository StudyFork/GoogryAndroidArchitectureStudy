package com.wybh.androidarchitecturestudy.model.repository

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl
import io.reactivex.Single

class RepositoryImpl(private val naverRemoteDataSource: NaverRemoteDataSourceImpl) : Repository {

    override fun searchCinema(query: String): Single<ResponseCinemaData> {
        return naverRemoteDataSource.searchCinema(query)
    }
}