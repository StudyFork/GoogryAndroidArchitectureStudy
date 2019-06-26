package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

class Repository private constructor(
    private val remoteDataSource: DataSource
) : DataSource {
    override fun requestMarkets(marketLike: String, callback: DataSource.RequestTickersCallback) {
        remoteDataSource.requestMarkets(marketLike, object : DataSource.RequestTickersCallback {
            override fun onTickersLoaded(tickers: List<Ticker>) =
                callback.onTickersLoaded(tickers)

            override fun onError(t: Throwable) =
                callback.onError(t)
        })
    }

    companion object {
        private var INSTANCE: Repository? = null

        operator fun invoke(RemoteDataSource: DataSource) =
            INSTANCE ?: Repository(RemoteDataSource)
                .apply { INSTANCE = this }
    }
}