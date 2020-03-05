package com.cnm.homework.data.source.remote

import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.remote.network.NaverApi
import io.reactivex.Single

class NaverQueryRemoteDataSourceImpl(private val naverApi: NaverApi) : NaverQueryRemoteDataSource {
    override fun getNaverMovie(query: String): Single<NaverResponse> =
        naverApi.getNaverMovie(query)
}