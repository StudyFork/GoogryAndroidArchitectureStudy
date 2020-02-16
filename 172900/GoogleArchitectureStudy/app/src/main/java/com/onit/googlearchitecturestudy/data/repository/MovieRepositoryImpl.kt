package com.onit.googlearchitecturestudy.data.repository

import com.onit.googlearchitecturestudy.Movies
import com.onit.googlearchitecturestudy.data.source.remote.NaverApiRemoteDataSource
import retrofit2.Response

class MovieRepositoryImpl(private val naverApiRemoteDataSource: NaverApiRemoteDataSource) : MovieRepository {

    override suspend fun getMovieList(keyword: String): Response<Movies> {
        return naverApiRemoteDataSource.getMovieList(keyword)
    }
}