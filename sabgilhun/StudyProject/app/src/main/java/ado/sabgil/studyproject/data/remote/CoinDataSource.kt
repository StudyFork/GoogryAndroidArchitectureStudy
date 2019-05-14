package ado.sabgil.studyproject.data.remote

import ado.sabgil.studyproject.data.model.Ticker
import io.reactivex.Observable
import io.reactivex.Single

interface CoinDataSource {

    fun loadMarketList(): Single<List<String>>

    fun subscribeCoinDataChange(): Observable<List<Ticker>>

    fun unSubscribeCoinDataChange()

}