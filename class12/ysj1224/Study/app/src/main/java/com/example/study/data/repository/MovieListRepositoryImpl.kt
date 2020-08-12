package com.example.study.data.repository

import com.example.study.data.model.NaverApiData
import com.example.study.data.remote.RemoteDataSource
import com.example.study.data.remote.RemoteDataSourceImpl

class MovieListRepositoryImpl(val remoteInterface: RemoteDataSource) : MovieListRepository {
    override fun doSearch(
        query: String,
        response: (List<NaverApiData.Item>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        remoteInterface.getSearch(
            query, response, fail
        )
    }
}