package com.example.study.data.repository

import com.example.study.data.datasource.remote.NaverSearchRemoteDataSource
import com.example.study.data.datasource.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.data.model.NaverSearchResponse
import io.reactivex.Single

class NaverSearchRepositoryImpl private constructor() : NaverSearchRepository {

    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource =
        NaverSearchRemoteDataSourceImpl()

    override fun getMovies(query: String): Single<NaverSearchResponse> {
        return naverSearchRemoteDataSource.getMovies(query)
    }

    companion object {
        private var instance: NaverSearchRepositoryImpl? = null

        fun getInstance(): NaverSearchRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: NaverSearchRepositoryImpl().also { instance = it}
            }
    }
}