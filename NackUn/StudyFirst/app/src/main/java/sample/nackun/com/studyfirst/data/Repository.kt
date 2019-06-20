package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

class Repository(
    private val remoteDataSource: DataSource
) : DataSource {
    override fun requestMarkets(marketLike: String, callback: DataSource.RequestTickersCallback) {
        remoteDataSource.requestMarkets(marketLike, object : DataSource.RequestTickersCallback {
            override fun onTickersLoaded(tickers: ArrayList<Ticker>) {
                callback.onTickersLoaded(tickers)
            }

            override fun onGetError(err: String?) {
                callback.onGetError(err)
            }

        })
    }

    companion object {
        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(RemoteDataSource: DataSource): Repository {
            return INSTANCE ?: Repository(RemoteDataSource)
                .apply { INSTANCE = this }
        }
    }
}