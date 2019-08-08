package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

class RepositoryImpl(
    private val remoteDataSource: DataSource
) : RepositoryInterface {
    override fun requestMarkets(
        marketLike: String,
        onTickersLoaded: (tickers: List<Ticker>) -> Unit,
        onError: (t: Throwable) -> Unit
    ) {
        remoteDataSource.requestMarkets(
            marketLike,
            onTickersLoaded,
            onError
        )
    }
}