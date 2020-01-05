package com.hansung.firstproject.data.source.remote

import com.hansung.firstproject.data.MovieResponseModel

interface NaverRemoteDataSource {
    fun getMoviesData(
        title: String,
        clientId: String,
        clientSecret: String,
        onResponse: (MovieResponseModel) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}