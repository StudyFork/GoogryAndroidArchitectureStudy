package ado.sabgil.studyproject.data

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.CoinDataSource
import ado.sabgil.studyproject.ext.setNetworkingThread
import io.reactivex.disposables.Disposable

class CoinRepositoryImpl(
    private val remoteDataSource: CoinDataSource
) : CoinRepository {

    override fun loadMarketList(
        success: (List<String>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable {
        return this.remoteDataSource
            .loadMarketList()
            .setNetworkingThread()
            .subscribe(success, fail)
    }

    override fun subscribeCoinDataByCurrency(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable {
        return this.remoteDataSource
            .subscribeCoinDataByCurrency(baseCurrency)
            .setNetworkingThread()
            .subscribe(success, fail)
    }

    override fun subscribeCoinDataByCoinName(
        coinName: String,
        success: (List<Ticker>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable {
        return this.remoteDataSource
            .subscribeCoinDataByCoinName(coinName)
            .setNetworkingThread()
            .subscribe(success, fail)
    }

    override fun unSubscribeCoinData() {
        this.remoteDataSource.unSubscribeCoinData()
    }

}