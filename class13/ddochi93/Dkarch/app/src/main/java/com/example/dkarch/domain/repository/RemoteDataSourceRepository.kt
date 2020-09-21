package com.example.dkarch.domain.repository

import com.example.dkarch.data.response.MovieResponse
import io.reactivex.Single
import retrofit2.Response

interface RemoteDataSourceRepository {
    fun getMovies(query: String): Single<Response<MovieResponse>>
}
