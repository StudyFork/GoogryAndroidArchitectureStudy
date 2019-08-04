package study.architecture.model.repository

import io.reactivex.Single
import study.architecture.model.datasource.LocalDataSource
import study.architecture.model.datasource.UpbitDataSource
import study.architecture.model.vo.Market
import study.architecture.model.vo.Ticker

class Repository private constructor(
    private val remoteDataSource: UpbitDataSource,
    private val localDataSource: LocalDataSource
) : UpbitDataSource {
    private var cacheIsDirty = false
    override fun getMarkets(): Single<List<Market>> {

        val a = localDataSource.getMarket()

        return remoteDataSource.getMarkets()
    }


    override fun getTickers(markets: String): Single<MutableList<Ticker>> =
        remoteDataSource.getTickers(markets)

    companion object {
        private var INSTANCE: Repository? = null

        fun getInstance(remoteDataSource: UpbitDataSource, localDataSource: LocalDataSource): Repository {
            if (INSTANCE == null) {
                INSTANCE = Repository(remoteDataSource, localDataSource)
            }
            return INSTANCE!!
        }
    }

}