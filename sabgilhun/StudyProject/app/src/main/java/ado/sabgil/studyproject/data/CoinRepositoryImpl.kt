package ado.sabgil.studyproject.data

import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitCoinDataSourceImpl
import ado.sabgil.studyproject.ext.setNetworkingThread
import io.reactivex.disposables.Disposable

object CoinRepositoryImpl : CoinRepository {

    private val dataSource = UpbitCoinDataSourceImpl

    override fun loadMarketList(
        success: (List<String>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .loadMarketList()
            .setNetworkingThread()
            .subscribe(success,
                {
                    fail.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
                }
            )
    }

    override fun subscribeCoinDataByCurrency(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .subscribeCoinDataByCurrency(baseCurrency)
            .setNetworkingThread()
            .subscribe(success,
                {
                    fail.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
                }
            )
    }

    override fun subscribeCoinDataByCoinName(
        coinName: String,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .subscribeCoinDataByCoinName(coinName)
            .setNetworkingThread()
            .subscribe(success,
                {
                    fail.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
                }
            )
    }

    override fun unSubscribeCoinData() {
        dataSource.unSubscribeCoinData()
    }

}