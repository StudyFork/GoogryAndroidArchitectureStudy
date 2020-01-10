package com.hansung.firstproject.data.source.remote

import com.hansung.firstproject.data.MovieResponseModel

interface NaverRemoteDataSource {
    fun getMoviesData(
        title: String,
        onResponse: (MovieResponseModel) -> Unit,
        onFailure: (Throwable) -> Unit,
        isEmptyList: () -> Unit
    )
}