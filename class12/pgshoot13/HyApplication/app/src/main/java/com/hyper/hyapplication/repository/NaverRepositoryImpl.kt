package com.hyper.hyapplication.repository

import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.source.remote.NaverRemoteDataSource

class NaverRepositoryImpl(private val movieDataSourceImpl: NaverRemoteDataSource) :
    NaverRepository {

    override fun movieSearch(
        query: String,
        success: (List<ResultGetSearchMovie.Item>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieDataSourceImpl.movieSearch(query = query, success = success, failure = failure)
    }
}