package com.hansung.repository

import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.remote.NaverRemoteDataSource

class NaverRepositoryImpl(
    private val dataSource: NaverRemoteDataSource
) : NaverRepository {
    override fun getMoviesData(
        title: String,
        success: (MovieResponseModel) -> Unit,
        fail: (Throwable) -> Unit,
        isEmptyList: () -> Unit
    ) {
        dataSource.getMoviesData(title, success, fail, isEmptyList)
    }
}