package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.vo.Ticker

class Repository(
    private val remoteDataSource: DataSource
) : DataSource
{
    override var tickers: ArrayList<Ticker> = remoteDataSource.tickers

    override fun requestMarkets(marketLike: String) {
        remoteDataSource.requestMarkets(marketLike)
    }

    override fun requestTickers(query: String) {
        remoteDataSource.requestTickers(query)
    }

    companion object {
        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(RemoteDataSource: DataSource): Repository{
            return INSTANCE ?: Repository(RemoteDataSource)
                .apply { INSTANCE = this }
        }
    }
}