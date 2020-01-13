package com.example.archstudy.data.repository

import com.example.archstudy.Item
import com.example.archstudy.data.source.local.NaverQueryLocalDataSource
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSource

interface NaverQueryRepository {
    fun getRemoteData(remoteData : NaverQueryRemoteDataSource) : List<Item>
    fun getLocalData(localData : NaverQueryLocalDataSource) : List<Item>
}