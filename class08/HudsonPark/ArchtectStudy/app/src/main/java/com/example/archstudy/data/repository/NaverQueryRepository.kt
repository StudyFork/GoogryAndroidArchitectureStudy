package com.example.archstudy.data.repository

import android.content.Context
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.MovieDataResponse
import retrofit2.Call

interface NaverQueryRepository {
    fun requestRemoteData(query: String): Call<MovieDataResponse>
    fun requestLocalData(query: String) : MutableList<MovieData>?
    fun insertLocalData(query: String, data : List<MovieData>, context : Context)
}