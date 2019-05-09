package ado.sabgil.studyproject.data.remote.upbit

import ado.sabgil.studyproject.data.model.Ticker
import io.reactivex.Observable
import io.reactivex.Single

interface UpbitApiHandler {

    fun getMarketList(): Single<List<String>>

    fun subscribeRemoteData(): Observable<List<Ticker>>

    fun unSubscribe()
}