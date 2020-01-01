package com.hansung.firstproject.data.source.remote

import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.network.NaverApiServiceImpl

interface NaverRemoteDataSource {
    fun getMoviesData(
        title: String,
        clientId: String,
        clientSecret: String,
        onResponse: (MovieResponseModel) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}