package com.hansung.firstproject.data.repository

import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSource
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSourceImpl

class NaverRepository {

    private var _INSTANCE: NaverRepository? = null

    private var naverRemoteDataSource: NaverRemoteDataSource? = null

    fun getInstance(dataSource: NaverRemoteDataSource): NaverRepository {
        if (_INSTANCE == null) {
            _INSTANCE = NaverRepository()
            naverRemoteDataSource = dataSource
        }
        return _INSTANCE!!
    }

    fun getMoviesData(
        title: String,
        clientId: String,
        clientSecret: String,
        success: (MovieResponseModel) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.getMoviesData(title, clientId, clientSecret, success, fail)
    }
}