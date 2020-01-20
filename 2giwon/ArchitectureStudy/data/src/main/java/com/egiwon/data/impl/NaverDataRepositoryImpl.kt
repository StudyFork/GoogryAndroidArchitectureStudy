package com.egiwon.data.impl

import com.egiwon.data.NaverDataRepository
import com.egiwon.data.NaverLocalDataSource
import com.egiwon.data.NaverRemoteDataSource
import com.egiwon.data.model.ContentEntity
import io.reactivex.Single

class NaverDataRepositoryImpl(
    private val naverRemoteDataSource: NaverRemoteDataSource,
    private val naverLocalDataSource: NaverLocalDataSource
) : NaverDataRepository {

    private fun loadRemoteContents(type: String, query: String): Single<ContentEntity> =
        naverRemoteDataSource.getRemoteContents(type, query)
            .flatMap { response ->
                naverLocalDataSource.saveContents(type, query, response)
                    .toSingle { response }
            }

    override fun getContents(type: String, query: String): Single<ContentEntity> =
        loadRemoteContents(type, query)

    override fun getContentsByHistory(type: String, query: String): Single<ContentEntity> =
        naverLocalDataSource.getLocalContents(type, query)

    override fun getContentQueries(type: String): Single<List<String>> =
        naverLocalDataSource.getContentQueries(type)

    override fun getCache(type: String): Single<ContentEntity> =
        naverLocalDataSource.getCacheContents(type)

}