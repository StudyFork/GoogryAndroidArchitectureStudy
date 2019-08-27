package com.test.androidarchitecture.data.source


class UpbitRepository private constructor() : UpbitRemoteDataSource {

    private val upbitRemoteDataSourceImpl = UpbitRemoteDataSourceImpl.getInstance()

    override fun getMarketAll() = upbitRemoteDataSourceImpl.getMarketAll()

    override fun getTicker(marketSearch: String) = upbitRemoteDataSourceImpl.getTicker(marketSearch)

    companion object {

        @Volatile
        private var instance: UpbitRepository? = null

        @JvmStatic
        fun getInstance(): UpbitRepository =
            instance ?: synchronized(this) {
                instance ?: UpbitRepository().also {
                    instance = it
                }
            }
    }

}