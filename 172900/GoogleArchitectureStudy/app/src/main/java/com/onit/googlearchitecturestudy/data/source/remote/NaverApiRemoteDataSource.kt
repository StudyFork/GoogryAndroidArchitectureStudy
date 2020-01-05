package com.onit.googlearchitecturestudy.data.source.remote

import com.onit.googlearchitecturestudy.Movies
import retrofit2.Response

interface NaverApiRemoteDataSource {
    suspend fun getMovieList(keyword: String): Response<Movies>
}