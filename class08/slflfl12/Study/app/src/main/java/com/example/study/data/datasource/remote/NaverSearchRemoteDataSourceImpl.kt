package com.example.study.data.datasource.remote

import com.example.study.data.datasource.remote.network.NaverApiClient
import com.example.study.data.model.NaverSearchResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {

    override fun getMovies(query: String): Single<NaverSearchResponse> {
        return NaverApiClient.naverRetrofitService.getMovieList(query)
    }
}