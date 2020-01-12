package com.onit.googlearchitecturestudy.data.source.remote

import com.onit.googlearchitecturestudy.Movies
import com.onit.googlearchitecturestudy.NaverApiService
import retrofit2.Response

class NaverApiRemoteDataSourceImpl(private val naverApiService: NaverApiService) :
    NaverApiRemoteDataSource {

    override suspend fun getMovieList(keyword: String): Response<Movies> {
        return naverApiService.getMovieList(keyword)
    }
}