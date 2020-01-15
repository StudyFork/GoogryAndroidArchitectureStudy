package com.example.archstudy.data.repository

import com.example.archstudy.MovieData
import retrofit2.Call

interface NaverQueryRepository {
    fun requestRemoteData(query: String): Call<MovieData>
    fun requestLocalData(query: String) : MovieData
}