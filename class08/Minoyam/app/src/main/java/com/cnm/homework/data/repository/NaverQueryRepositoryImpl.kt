package com.cnm.homework.data.repository

import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSourceImpl
import com.cnm.homework.network.model.NaverResponse
import io.reactivex.Single

class NaverQueryRepositoryImpl(private val remoteDataSource: NaverQueryRemoteDataSourceImpl) :
    NaverQueryRepository {
    override fun getNaverMovie(query: String): Single<NaverResponse> {
        return remoteDataSource.getNaverMovie(query)
    }
}