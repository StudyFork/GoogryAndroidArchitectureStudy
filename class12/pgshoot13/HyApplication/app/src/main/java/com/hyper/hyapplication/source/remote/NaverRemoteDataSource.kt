package com.hyper.hyapplication.source.remote

import com.hyper.hyapplication.model.ResultGetSearchMovie

interface NaverRemoteDataSource {
    fun movieSearch(
        query: String,
        success: (List<ResultGetSearchMovie.Item>) -> Unit,
        failure: (Throwable) -> Unit
    )
}