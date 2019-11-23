package com.egiwon.architecturestudy.data.source

import com.egiwon.architecturestudy.data.Content

class NaverDataRepository(
    private val naverRemoteDataSource: NaverDataSource
) : NaverDataSource {

    override fun getContents(
        type: String,
        query: String,
        onSuccess: (resultList: List<Content.Item>) -> Unit,
        onFailure: (throwable: Throwable) -> Unit
    ) = naverRemoteDataSource.getContents(
            type,
            query,
        onSuccess,
        onFailure
    )


    companion object {
        private var instance: NaverDataRepository? = null

        fun getInstance(naverDataSource: NaverDataSource): NaverDataRepository =
            instance ?: NaverDataRepository(naverDataSource).apply {
                instance = this
            }
    }
}