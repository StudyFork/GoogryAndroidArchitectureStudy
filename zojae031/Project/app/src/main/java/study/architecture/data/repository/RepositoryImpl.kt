package study.architecture.data.repository

import android.net.ConnectivityManager
import io.reactivex.Single
import study.architecture.data.Repository
import study.architecture.data.UpbitLocalDataSource
import study.architecture.data.UpbitRemoteDataSource
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker

class RepositoryImpl private constructor(
    private val remoteDataSource: UpbitRemoteDataSource,
    private val localDataSource: UpbitLocalDataSource,
    private val manager: ConnectivityManager
) : Repository {


    override fun getMarkets(): Single<List<Market>> {
        return if (!checkNetwork()) {
            localDataSource.getMarkets()
        } else {
            remoteDataSource.getMarkets()
                .doOnSuccess { list -> list.map { localDataSource.insertMarket(it) } }
        }
    }


    override fun getTickers(markets: String): Single<MutableList<Ticker>> {
        return if (!checkNetwork()) {
            localDataSource.getTickers(markets.split("-")[0])
        } else {
            remoteDataSource.getTickers(markets)
                .doOnSuccess { list -> list.map { localDataSource.insertTicker(it) } }
        }

    }

    override fun checkNetwork(): Boolean {
        if (manager.activeNetworkInfo == null) {
            return false
        }
        return manager.activeNetworkInfo.isConnected
    }

    companion object {
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(
            remoteRemoteDataSource: UpbitRemoteDataSource,
            localDataSource: UpbitLocalDataSource,
            manager: ConnectivityManager
        ): RepositoryImpl {
            if (INSTANCE == null) {
                INSTANCE =
                    RepositoryImpl(remoteRemoteDataSource, localDataSource, manager)
            }
            return INSTANCE!!
        }
    }

}