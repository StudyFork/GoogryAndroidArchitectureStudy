package ado.sabgil.studyproject.data

import ado.sabgil.studyproject.data.model.Ticker
import io.reactivex.disposables.Disposable

interface CoinRepository {

    fun loadMarketList(
        success: (List<String>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable

    fun subscribeCoinDataByCurrency(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable

    fun subscribeCoinDataByCoinName(
        coinName: String,
        success: (List<Ticker>) -> Unit,
        fail: (Throwable) -> Unit
    ): Disposable

    fun unSubscribeCoinData()

}