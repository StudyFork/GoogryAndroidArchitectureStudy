package study.architecture.model.repository

import io.reactivex.Single
import study.architecture.model.datasource.LocalDataSource
import study.architecture.model.datasource.UpbitDataSource
import study.architecture.model.entity.Market
import study.architecture.model.entity.Ticker

class Repository private constructor(
    private val remoteDataSource: UpbitDataSource,
    private val localDataSource: LocalDataSource
) : UpbitDataSource {

    override fun getMarkets(): Single<List<Market>> {
        return if (!localDataSource.checkNetwork()) {
            localDataSource.getMarket()
        } else {
            remoteDataSource.getMarkets()
        }
    }

    fun insertMarket(lists: List<Market>) {
        lists.map { localDataSource.insertMarket(it) }
    }

    fun insertTicker(lists: MutableList<Ticker>) {
        lists.map { localDataSource.insertTicker(it) }
    }

    override fun getTickers(markets: String): Single<MutableList<Ticker>> {
        return if (!localDataSource.checkNetwork()) {
            localDataSource.getTicker(markets.split("-")[0])
        } else {
            remoteDataSource.getTickers(markets)
        }

    }


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