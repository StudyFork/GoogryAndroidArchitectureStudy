package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

class Repository(
    val remoteDataSource: DataSource
) : DataSource
{
    override var tickers: ArrayList<Ticker> = remoteDataSource.tickers

    override fun requestMarkets(marketLike: String) {
        remoteDataSource.requestMarkets(marketLike)
    }

    override fun requestTickers(query: String) {
        remoteDataSource.requestTickers(query)
    }
}