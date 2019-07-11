package sample.nackun.com.studyfirst.data

import sample.nackun.com.studyfirst.vo.Ticker

class Repository private constructor(
    private val remoteDataSource: DataSource
) : DataSource {
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

    companion object {
        private var INSTANCE: Repository? = null

        operator fun invoke(RemoteDataSource: DataSource) =
            INSTANCE ?: Repository(RemoteDataSource)
                .apply { INSTANCE = this }
    }
}