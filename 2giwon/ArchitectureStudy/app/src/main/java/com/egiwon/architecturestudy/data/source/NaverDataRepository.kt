package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.Content

class NaverDataRepository(
    private val naverRemoteDataSource: NaverDataSource
) : NaverDataSource {

    override fun getContents(
        type: String,
        query: String,
        callback: NaverDataSource.Callback
    ) = naverRemoteDataSource.getContents(
            type,
            query,
            object : NaverDataSource.Callback {
                override fun onSuccess(list: List<Content.Item>) {
                    callback.onSuccess(list)
                }

                override fun onFailure(throwable: Throwable) {
                    callback.onFailure(throwable)
                }
            })


    companion object {
        private var instance: NaverDataRepository? = null

        fun getInstance(naverDataSource: NaverDataSource): NaverDataRepository =
            instance ?: NaverDataRepository(naverDataSource).apply {
                instance = this
            }
    }
}