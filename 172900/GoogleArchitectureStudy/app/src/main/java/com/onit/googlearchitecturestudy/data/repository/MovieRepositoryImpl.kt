package com.onit.googlearchitecturestudy.data.repository

import com.onit.googlearchitecturestudy.Movies
import com.onit.googlearchitecturestudy.data.source.remote.NaverApiRemoteDataSource
import com.onit.googlearchitecturestudy.data.source.remote.NaverApiRemoteDataSourceImpl
import retrofit2.Response

class MovieRepositoryImpl : MovieRepository {

    private val naverApiRemoteDataSource: NaverApiRemoteDataSource = NaverApiRemoteDataSourceImpl()

    override suspend fun getMovieList(keyword: String): Response<Movies> {
        return naverApiRemoteDataSource.getMovieList(keyword)
    }
}