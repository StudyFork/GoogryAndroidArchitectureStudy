package com.hansung.firstproject.data.repository

import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSource

class NaverRepository(dataSource: NaverRemoteDataSource) {

    private var _INSTANCE: NaverRepository? = null

    private var naverRemoteDataSource: NaverRemoteDataSource = dataSource

    fun getInstance(): NaverRepository {
        if (_INSTANCE == null)
            _INSTANCE = NaverRepository(naverRemoteDataSource)
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