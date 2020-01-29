package com.example.archstudy.data.repository

import com.example.archstudy.MovieDataResponse
import com.example.archstudy.data.source.local.MovieData
import retrofit2.Call

interface NaverQueryRepository {
    fun requestLocalData(query: String) : MutableList<MovieData>?
    fun insertLocalData(query: String, data : List<MovieData>)
    fun requestRemoteData(
        query: String,
        successCallback: (MutableList<MovieData>) -> Unit,
        failCallback: (Throwable) -> Unit
    )
}