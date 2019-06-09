package ado.sabgil.studyproject.data

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitCoinDataSourceImpl
import ado.sabgil.studyproject.ext.setNetworkingThread
import io.reactivex.disposables.Disposable

object CoinRepositoryImpl : CoinRepository {

    private val dataSource = UpbitCoinDataSourceImpl

    override fun loadMarketList(
        success: (List<String>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable {
        return dataSource
            .loadMarketList()
            .setNetworkingThread()
            .subscribe(success, fail)
    }

    override fun subscribeCoinDataByCurrency(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable {
        return dataSource
            .subscribeCoinDataByCurrency(baseCurrency)
            .setNetworkingThread()
            .subscribe(success, fail)
    }

    override fun subscribeCoinDataByCoinName(
        coinName: String,
        success: (List<Ticker>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable {
        return dataSource
            .subscribeCoinDataByCoinName(coinName)
            .setNetworkingThread()
            .subscribe(success, fail)
    }

    override fun unSubscribeCoinData() {
        dataSource.unSubscribeCoinData()
    }

}