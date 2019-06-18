package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.data.remote.RemoteDataSource

class Repository(
    val remoteDataSource: DataSource
) : DataSource
{
    override fun requestMarkets(marketLike: String) {
        remoteDataSource.requestMarkets(marketLike)
    }

    override fun requestTickers(query: String) {
        remoteDataSource.requestTickers(query)
    }

}