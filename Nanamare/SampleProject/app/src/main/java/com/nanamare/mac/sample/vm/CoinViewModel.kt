package com.nanamare.mac.sample.vm

import androidx.databinding.ObservableField
import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.base.BaseViewModel
import com.nanamare.mac.sample.data.coin.CoinRepository
import io.reactivex.subjects.PublishSubject

class CoinViewModel: BaseViewModel() {

    var coins = ObservableField<List<CoinModel>>(mutableListOf())

    var coinsObservable: PublishSubject<List<CoinModel>> = PublishSubject.create()

    fun getCoins(ticketList: MutableList<String>) {
        CoinRepository.getCoins(ticketList, success = {
            coins.set(it)
            coinsObservable.onNext(it)
        }, failed = {
            coinsObservable.onError(Throwable())
        })
    }

    override fun close() {
        CoinRepository.close()
    }

}