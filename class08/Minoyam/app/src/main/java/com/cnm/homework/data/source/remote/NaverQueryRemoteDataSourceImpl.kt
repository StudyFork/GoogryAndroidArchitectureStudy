package com.cnm.homework.data.source.remote

import com.cnm.homework.data.source.remote.network.NetworkHelper
import com.cnm.homework.network.model.NaverResponse
import io.reactivex.Single

class NaverQueryRemoteDataSourceImpl : NaverQueryRemoteDataSource
{
    override fun getNaverMovie(query: String): Single<NaverResponse> {
        return NetworkHelper.naverApi.getNaverMovie(query)
    }
}