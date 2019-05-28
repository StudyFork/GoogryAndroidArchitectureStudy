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

    override fun getCoinDataChangeWithCurrency(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .subscribeCoinDataChange()
            .setNetworkingThread()
            .subscribe(
                { result ->
                    success.invoke(result.filter { it.base == baseCurrency })
                }, {
                    fail.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
                }
            )
    }

    override fun getCoinDataChangeWithCoinName(
        coinName: String,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    ): Disposable {
        return dataSource
            .subscribeCoinDataChange()
            .setNetworkingThread()
            .subscribe(
                { result ->
                    success.invoke(result.filter { it.coinName == coinName })
                }, {
                    fail.invoke("서버에서 데이터를 가져오는데에 실패하였습니다.")
                }
            )
    }

    override fun unSubscribeCoinDataChange() {
        dataSource.unSubscribeCoinDataChange()
    }

}