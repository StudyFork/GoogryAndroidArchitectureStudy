package com.example.study.data.repository

import com.example.study.data.source.remote.NaverSearchRemoteDataSource
import com.example.study.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.data.model.NaverSearchResponse
import com.example.study.data.source.local.NaverSearchLocalDataSource
import io.reactivex.Single

class NaverSearchRepositoryImpl private constructor(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {


    override fun getMovies(query: String): Single<NaverSearchResponse> {
        return naverSearchRemoteDataSource.getMovies(query)
    }

    companion object {
        private var instance: NaverSearchRepositoryImpl? = null

        fun getInstance(naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
                        naverSearchLocalDataSource: NaverSearchLocalDataSource): NaverSearchRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: NaverSearchRepositoryImpl(naverSearchRemoteDataSource, naverSearchLocalDataSource).also { instance = it }
            }
    }
}