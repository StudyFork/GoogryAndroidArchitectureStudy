package com.cnm.homework.data.repository

import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.local.NaverQueryLocalDataSource
import com.cnm.homework.data.source.local.db.LocalEntity
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSource
import io.reactivex.Single

class NaverQueryRepositoryImpl(
    private val remoteDataSource: NaverQueryRemoteDataSource,
    private val localDataSoure: NaverQueryLocalDataSource
) : NaverQueryRepository {


    override fun getNaverMovie(query: String): Single<NaverResponse> =
        remoteDataSource.getNaverMovie(query)
            .doOnSuccess {
                saveCacheMovie(LocalEntity(it.items[0]))
            }


    override fun saveCacheMovie(localEntity: LocalEntity): Single<Unit> =
        localDataSoure.saveCacheMovie(localEntity)

    override fun loadLocal(): List<NaverResponse.Item> {
        return mutableListOf<NaverResponse.Item>()
            .apply {
                localDataSoure.loadLocal().forEach { add(it.repo) }
            }
    }

}
