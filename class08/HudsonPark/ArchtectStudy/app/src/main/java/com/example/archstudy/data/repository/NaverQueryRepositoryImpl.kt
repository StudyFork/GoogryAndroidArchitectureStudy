package com.example.archstudy.data.repository

import com.example.archstudy.Item
import com.example.archstudy.data.source.local.NaverQueryLocalDataSource
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSource

class NaverQueryRepositoryImpl(private val query: String) : NaverQueryRepository {

    override fun getRemoteData(remoteData: NaverQueryRemoteDataSource): List<Item> =
        remoteData.getMovie(query)

    override fun getLocalData(localData: NaverQueryLocalDataSource): List<Item> =
        localData.getMovie(query)
}