package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSourceImpl

object NaverDataRepository : NaverRemoteDataSource {

    private val naverRemoteDataSource = NaverRemoteDataSourceImpl
    override fun getContents(
        type: String,
        query: String,
        callback: NaverRemoteDataSource.Callback
    ) {
        naverRemoteDataSource.getContents(
            type,
            query,
            object : NaverRemoteDataSource.Callback {
                override fun onSuccess(list: List<Content.Item>) {
                    callback.onSuccess(list)
                }

                override fun onFailure(throwable: Throwable) {
                    callback.onFailure(throwable)
                }
            })
    }
}