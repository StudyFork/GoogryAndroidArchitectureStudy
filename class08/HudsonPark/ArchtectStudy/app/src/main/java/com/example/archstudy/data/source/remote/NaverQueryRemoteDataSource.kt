package com.example.archstudy.data.source.remote

import com.example.archstudy.MovieData
import retrofit2.Call

interface NaverQueryRemoteDataSource {
    fun getMovie(query: String): Call<MovieData>
}