package com.hyper.hyapplication.repository

import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.source.remote.NaverRemoteDataSource
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl

class NaverRepositoryImpl : NaverRepository {
    private val movieDataSourceImpl: NaverRemoteDataSource by lazy {
        NaverRemoteDataSourceImpl()
    }

    override fun movieSearch(
        query: String,
        success: (List<ResultGetSearchMovie.Item>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieDataSourceImpl.movieSearch(query = query, success = success, failure = failure)
    }
}