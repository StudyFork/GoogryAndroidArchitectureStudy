package com.cnm.homework.data.source.remote

import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.remote.network.NetworkHelper
import io.reactivex.Single

class NaverQueryRemoteDataSourceImpl : NaverQueryRemoteDataSource {
    override fun getNaverMovie(query: String): Single<NaverResponse> =
        NetworkHelper.naverApi.getNaverMovie(query)
}