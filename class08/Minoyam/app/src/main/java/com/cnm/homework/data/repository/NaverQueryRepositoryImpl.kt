package com.cnm.homework.data.repository

import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.local.NaverQueryLocalDataSourceImpl
import com.cnm.homework.data.source.local.db.LocalEntity
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSourceImpl
import io.reactivex.Single

class NaverQueryRepositoryImpl(
    private val remoteDataSource: NaverQueryRemoteDataSourceImpl,
    private val localDataSoure: NaverQueryLocalDataSourceImpl
) : NaverQueryRepository {


    override fun getNaverMovie(query: String): Single<NaverResponse> =
        remoteDataSource.getNaverMovie(query)
            .doOnSuccess {
                saveCacheMovie(LocalEntity(it.items[0]))
            }


    override fun saveCacheMovie(localEntity: LocalEntity): Single<Unit> =
        localDataSoure.saveCacheMovie(localEntity)

    override fun loadLocal(): List<LocalEntity> {
        return localDataSoure.loadLocal()
    }


}
