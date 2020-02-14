package com.example.myapplication.data.repository

import com.example.myapplication.Movie
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.source.NaverLocalDataSource
import com.example.myapplication.data.source.NaverRemoteDataSource

class NaverRepositoryImpl(
    val naverRemoteDataSource: NaverRemoteDataSource,
    val naverLocalDataSource: NaverLocalDataSource
) : NaverRepository {

    override fun getResultData(
        query: String,
        success: (MovieResult) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.getResultData(query, success, fail)
    }

    override fun getRecentData(): Movie {
        return naverLocalDataSource.getRecentData()
    }

    override fun saveCache(movie: Movie) {
        naverLocalDataSource.saveCache(movie)
    }

}