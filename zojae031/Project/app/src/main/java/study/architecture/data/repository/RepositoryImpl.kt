package study.architecture.data.repository

import android.net.ConnectivityManager
import io.reactivex.Single
import study.architecture.data.entity.Market
import study.architecture.data.entity.Ticker
import study.architecture.data.local.UpbitLocalDataSource
import study.architecture.data.remote.UpbitRemoteDataSource

class RepositoryImpl private constructor(
    private val remoteRemoteDataSource: UpbitRemoteDataSource,
    private val localRemoteDataSource: UpbitLocalDataSource,
    private val manager: ConnectivityManager
) : Repository {


    override fun getMarkets(): Single<List<Market>> {
        return if (!checkNetwork()) {
            localRemoteDataSource.getMarkets()
        } else {
            remoteRemoteDataSource.getMarkets()
                .doOnSuccess { list -> list.map { localRemoteDataSource.insertMarket(it) } }
        }
    }


    override fun getTickers(markets: String): Single<MutableList<Ticker>> {
        return if (!checkNetwork()) {
            localRemoteDataSource.getTickers(markets.split("-")[0])
        } else {
            remoteRemoteDataSource.getTickers(markets)
                .doOnSuccess { list -> list.map { localRemoteDataSource.insertTicker(it) } }
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