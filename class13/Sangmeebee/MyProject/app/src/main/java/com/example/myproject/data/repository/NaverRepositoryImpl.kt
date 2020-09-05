package com.example.myproject.data.repository

import com.example.myproject.data.model.Items
import com.example.myproject.data.source.local.NaverLocalDataSource
import com.example.myproject.data.source.remote.NaverRemoteDataSource

class NaverRepositoryImpl(
    private val naverLocalDataSource: NaverLocalDataSource,
    private val naverRemoteDataSource: NaverRemoteDataSource
) : NaverRepository {
    override fun getMovieList(
        title: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        naverRemoteDataSource.getMovieList(title, success, failed)
    }
}
