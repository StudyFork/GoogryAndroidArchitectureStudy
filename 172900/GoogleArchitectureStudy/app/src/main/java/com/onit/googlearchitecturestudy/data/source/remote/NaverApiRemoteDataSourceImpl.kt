package com.onit.googlearchitecturestudy.data.source.remote

import com.onit.googlearchitecturestudy.Movies
import com.onit.googlearchitecturestudy.NetworkService
import retrofit2.Response

class NaverApiRemoteDataSourceImpl : NaverApiRemoteDataSource {

    private val naverApiService = NetworkService.naverApiService

    override suspend fun getMovieList(keyword: String): Response<Movies> {
        return naverApiService.getMovieList(keyword)
    }
}