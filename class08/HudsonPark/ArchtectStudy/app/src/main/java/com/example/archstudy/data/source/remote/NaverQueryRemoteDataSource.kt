package com.example.archstudy.data.source.remote

import com.example.archstudy.MovieDataResponse
import retrofit2.Call

interface NaverQueryRemoteDataSource {
    fun getMovie(query: String): Call<MovieDataResponse>
}