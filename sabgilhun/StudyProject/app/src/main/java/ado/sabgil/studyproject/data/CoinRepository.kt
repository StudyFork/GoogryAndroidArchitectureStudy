package ado.sabgil.studyproject.data

import ado.sabgil.studyproject.data.model.Ticker
import io.reactivex.disposables.Disposable

interface CoinRepository {

    fun loadMarketList(
        success: (List<String>) -> Unit,
        fail: (String) -> Unit
    ): Disposable

    fun subscribeCoinDataChange(
        baseCurrency: String,
        success: (List<Ticker>) -> Unit,
        fail: (String) -> Unit
    ): Disposable

    fun unSubscribeCoinDataChange()

}