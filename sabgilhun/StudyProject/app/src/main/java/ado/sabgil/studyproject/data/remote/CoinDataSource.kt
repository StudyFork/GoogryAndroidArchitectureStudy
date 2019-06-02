package ado.sabgil.studyproject.data.remote

import ado.sabgil.studyproject.data.model.Ticker
import io.reactivex.Observable
import io.reactivex.Single

interface CoinDataSource {

    fun loadMarketList(): Single<List<String>>

    fun subscribeCoinDataByCurrency(
        baseCurrency: String
    ): Observable<List<Ticker>>

    fun subscribeCoinDataByCoinName(
        coinName: String
    ): Observable<List<Ticker>>

    fun unSubscribeCoinData()

}