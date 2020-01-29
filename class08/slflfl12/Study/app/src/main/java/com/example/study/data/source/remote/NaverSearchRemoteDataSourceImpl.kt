package com.example.study.data.source.remote

import com.example.study.data.source.remote.network.NaverApiClient
import com.example.study.data.model.NaverSearchResponse
import io.reactivex.Single

class NaverSearchRemoteDataSourceImpl :
    NaverSearchRemoteDataSource {

    override fun getMovies(query: String): Single<NaverSearchResponse> {
        return NaverApiClient.naverRetrofitService.getMovieList(query)
    }

    companion object {
        private var instance : NaverSearchRemoteDataSourceImpl? = null

        fun getInstance(): NaverSearchRemoteDataSourceImpl {
            return instance ?: synchronized(NaverSearchRemoteDataSourceImpl::javaClass) {
                instance ?: NaverSearchRemoteDataSourceImpl().also { instance = it}
            }

        }
    }
}