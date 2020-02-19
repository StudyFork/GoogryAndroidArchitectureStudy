package com.hansung.repository

import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.remote.NaverRemoteDataSource

class NaverRepository(
    private val dataSource: NaverRemoteDataSource
) {
    fun getMoviesData(
        title: String,
        success: (MovieResponseModel) -> Unit,
        fail: (Throwable) -> Unit,
        isEmptyList: () -> Unit
    ) {
        dataSource.getMoviesData(title, success, fail, isEmptyList)
    }
}