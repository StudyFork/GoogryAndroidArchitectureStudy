package com.egiwon.architecturestudy.data.source

class NaverDataRepository(
    private val naverRemoteDataSource: NaverDataSource
) : NaverDataSource {

    override fun getContents(
        type: String,
        query: String
    ) = naverRemoteDataSource.getContents(
        type,
        query
    )


    companion object {
        private var instance: NaverDataRepository? = null

        fun getInstance(naverDataSource: NaverDataSource): NaverDataRepository =
            instance ?: NaverDataRepository(naverDataSource).apply {
                instance = this
            }
    }
}