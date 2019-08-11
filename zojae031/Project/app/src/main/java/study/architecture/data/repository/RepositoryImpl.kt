package study.architecture.data.repository

import io.reactivex.Single
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker
import study.architecture.data.local.LocalDataSourceImpl
import study.architecture.data.local.UpbitLocalDataSource
import study.architecture.data.remote.UpbitRemoteDataSource

class RepositoryImpl private constructor(
    private val remoteRemoteDataSource: UpbitRemoteDataSource,
    private val localRemoteDataSource: UpbitLocalDataSource
) : Repository {

    override fun getMarkets(): Single<List<Market>> {
        return if (!localRemoteDataSource.checkNetwork()) {
            localRemoteDataSource.getMarkets()
        } else {
            remoteRemoteDataSource.getMarkets()
                .doOnSuccess { list -> list.map { localRemoteDataSource.insertMarket(it) } }
        }
    }


    override fun getTickers(markets: String): Single<MutableList<Ticker>> {
        return if (!localRemoteDataSource.checkNetwork()) {
            localRemoteDataSource.getTickers(markets.split("-")[0])
        } else {
            remoteRemoteDataSource.getTickers(markets)
                .doOnSuccess { list -> list.map { localRemoteDataSource.insertTicker(it) } }
        }

    }


    companion object {
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(
            remoteRemoteDataSource: UpbitRemoteDataSource,
            localDataSource: LocalDataSourceImpl
        ): RepositoryImpl {
            if (INSTANCE == null) {
                INSTANCE =
                    RepositoryImpl(remoteRemoteDataSource, localDataSource)
            }
            return INSTANCE!!
        }
    }

}