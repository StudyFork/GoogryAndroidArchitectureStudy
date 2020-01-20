package com.example.archstudy.data.repository

import com.example.archstudy.MovieDataResponse
import com.example.archstudy.data.source.local.MovieData
import retrofit2.Call

interface NaverQueryRepository {
    fun requestRemoteData(query: String): Call<MovieDataResponse>
    fun requestLocalData(query: String) : MutableList<MovieData>?
    fun insertLocalData(query: String, data : List<MovieData>)
}