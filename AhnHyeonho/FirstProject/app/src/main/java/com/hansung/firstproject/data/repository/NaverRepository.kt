package com.hansung.firstproject.data.repository

import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSource

class NaverRepository private constructor() {

    fun getMoviesData(
        title: String,
        clientId: String,
        clientSecret: String,
        success: (MovieResponseModel) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        dataSource.getMoviesData(title, clientId, clientSecret, success, fail)
    }

    companion object {
        @Volatile
        private var _INSTANCE: NaverRepository? = null
        private lateinit var dataSource: NaverRemoteDataSource

        @JvmStatic
        fun getInstance(dataSource: NaverRemoteDataSource): NaverRepository =
            _INSTANCE ?: synchronized(this) {
                _INSTANCE ?: NaverRepository().also {
                    _INSTANCE = it
                    this.dataSource = dataSource
                }
            }
    }
}