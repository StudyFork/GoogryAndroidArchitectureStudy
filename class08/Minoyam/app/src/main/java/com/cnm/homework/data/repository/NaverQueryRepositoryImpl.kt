package com.cnm.homework.data.repository

import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSource
import io.reactivex.Single

class NaverQueryRepositoryImpl(
    private val remoteDataSource: NaverQueryRemoteDataSource
//    private val localDataSoure: NaverQueryLocalDataSource
) : NaverQueryRepository {


    override fun getNaverMovie(query: String): Single<NaverResponse> =
        remoteDataSource.getNaverMovie(query)
            .doOnSuccess {
                //                local 작업
            }


//    override fun saveCacheMovie(localEntity: LocalEntity): Single<Unit> =
//        localDataSoure.saveCacheMovie(localEntity)

//    override fun loadLocal(): LiveData<List<LocalEntity>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }


    companion object {
        private var INSTANCE: NaverQueryRepositoryImpl? = null

        fun getInstance(
            remoteDataSource: NaverQueryRemoteDataSource
//            localDataSource: NaverQueryLocalDataSource
        ): NaverQueryRepository =
            INSTANCE ?: NaverQueryRepositoryImpl(remoteDataSource).apply {
                INSTANCE = this
            }
    }
}
