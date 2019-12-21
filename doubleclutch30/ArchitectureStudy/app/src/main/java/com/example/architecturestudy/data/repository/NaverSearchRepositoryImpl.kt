package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl

class NaverSearchRepositoryImpl : NaverSearchRepository {

    override val naverSearchRemoteDataSource: NaverSearchRemoteDataSource by lazy {
        NaverSearchRemoteDataSourceImpl()
    }

    override fun getMovie(
        keyword: String,
        success: (MovieData) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }
}