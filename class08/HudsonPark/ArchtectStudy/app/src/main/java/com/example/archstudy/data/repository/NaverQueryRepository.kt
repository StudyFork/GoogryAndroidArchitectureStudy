package com.example.archstudy.data.repository

import com.example.archstudy.data.source.local.MovieData

interface NaverQueryRepository {
    fun requestLocalData(
        query: String,
        successCallback: (MutableList<MovieData>) -> Unit,
        failCallback: (Throwable) -> Unit
    ): MutableList<MovieData>?

    fun insertLocalData(query: String, data: List<MovieData>)
    fun requestRemoteData(
        query: String,
        successCallback: (MutableList<MovieData>) -> Unit,
        failCallback: (Throwable) -> Unit
    )
}