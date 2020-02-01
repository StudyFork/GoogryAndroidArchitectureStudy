package com.cnm.homework.data.repository

import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.local.NaverQueryLocalDataSource
import com.cnm.homework.data.source.local.db.LocalEntity
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSource
import io.reactivex.Single

class NaverQueryRepositoryImpl(
    private val remoteDataSource: NaverQueryRemoteDataSource,
    private val localDataSource: NaverQueryLocalDataSource
) : NaverQueryRepository {


    override fun getNaverMovie(query: String): Single<NaverResponse> =
        remoteDataSource.getNaverMovie(query)
            .doOnSuccess {
                saveCacheMovie(LocalEntity(0, it.items))
            }


    override fun saveCacheMovie(localEntity: LocalEntity) =
        localDataSource.saveCacheMovie(localEntity)

    override fun loadLocal(): List<NaverResponse.Item> =
        mutableListOf<NaverResponse.Item>()
            .apply {
                localDataSource.loadLocal().map {
                    this.addAll(it.repo)
                }
            }


}
