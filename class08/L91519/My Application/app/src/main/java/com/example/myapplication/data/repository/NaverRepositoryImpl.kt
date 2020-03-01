package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.source.local.NaverLocalDataSource
import com.example.myapplication.data.source.remote.NaverRemoteDataSource

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

}