package com.egiwon.architecturestudy.data

import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.remote.response.ContentResponse
import io.reactivex.Single

class NaverDataRepositoryImpl(
    private val naverRemoteDataSource: NaverDataSource.Remote,
    private val naverLocalDataSource: NaverDataSource.Local
) : NaverDataRepository {

    override fun getContents(type: String, query: String): Single<ContentResponse> =
        naverRemoteDataSource.getContents(type, query)

    companion object {
        private var instance: NaverDataRepositoryImpl? = null

        fun getInstance(
            naverDataSource: NaverDataSource.Remote,
            naverLocalDataSource: NaverDataSource.Local
        ): NaverDataRepositoryImpl =
            instance
                ?: NaverDataRepositoryImpl(
                    naverDataSource,
                    naverLocalDataSource
                ).apply {
                    instance = this
                }
    }
}