package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.source.NaverLocalDataSource
import com.example.myapplication.data.source.NaverRemoteDataSource

class NaverRepositoryImpl(
    private val naverRemoteDataSource: NaverRemoteDataSource,
    private val naverLocalDataSource: NaverLocalDataSource
) : NaverRepository {

    override fun getResultData(
        query: String,
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.getResultData(query, {
            naverLocalDataSource.saveCache(Movie(it.items, query))
            success(it)
        }, fail)
    }

    override fun getRecentData(): Movie {
        return naverLocalDataSource.getRecentData()
    }
    companion object {
        private var instance: NaverRepositoryImpl? = null
        fun getInstance(
            naverRemoteDataSource: NaverRemoteDataSource,
            naverLocalDataSource: NaverLocalDataSource
        ): NaverRepositoryImpl {
            return instance ?: NaverRepositoryImpl(
                naverRemoteDataSource,
                naverLocalDataSource
            ).apply { instance = this }
        }
    }

}