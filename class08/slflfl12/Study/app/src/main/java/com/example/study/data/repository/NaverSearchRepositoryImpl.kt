package com.example.study.data.repository

import com.example.study.data.source.remote.NaverSearchRemoteDataSource
import com.example.study.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.data.model.NaverSearchResponse
import com.example.study.data.source.local.NaverSearchLocalDataSource
import io.reactivex.Single

class NaverSearchRepositoryImpl private constructor(
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource,
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource
) : NaverSearchRepository {


    override fun getMovies(query: String): Single<NaverSearchResponse> {
        return naverSearchRemoteDataSource.getMovies(query)
    }

    companion object {
        private var instance: NaverSearchRepositoryImpl? = null

        fun getInstance(
            naverSearchLocalDataSource: NaverSearchLocalDataSource,
            naverSearchRemoteDataSource: NaverSearchRemoteDataSource
        ): NaverSearchRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: NaverSearchRepositoryImpl(
                    naverSearchLocalDataSource,
                    naverSearchRemoteDataSource
                ).also { instance = it }
            }
    }
}