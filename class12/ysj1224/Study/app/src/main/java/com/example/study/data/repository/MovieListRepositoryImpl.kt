package com.example.study.data.repository

import com.example.study.data.model.NaverApiData
import com.example.study.data.remote.RemoteDataSourceImpl

class MovieListRepositoryImpl : MovieListRepository {
    private val remoteInterfaceImpl = RemoteDataSourceImpl()

    override fun doSearch(
        query: String,
        response: (List<NaverApiData.Item>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        remoteInterfaceImpl.getSearch(
            query, response, fail
        )
    }
}