package com.hyper.hyapplication.repository

import com.hyper.hyapplication.model.ResultGetSearchMovie

interface NaverRepository {
    fun movieSearch(
        query: String,
        success: (List<ResultGetSearchMovie.Item>) -> Unit,
        failure: (Throwable) -> Unit
    )
}