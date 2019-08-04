package com.nanamare.mac.sample.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.base.BaseViewModel
import com.nanamare.mac.sample.data.coin.CoinSource

class CoinViewModel(private val coinSource: CoinSource): BaseViewModel() {

    private val _coins = MutableLiveData<List<CoinModel>>(mutableListOf())

    val coins: LiveData<List<CoinModel>> get() = _coins

    fun getCoins(ticketList: MutableList<String>) {
        isLoadingObservable.value = true
        coinSource.getCoins(ticketList, success = {
            _coins.value = it
            isLoadingObservable.value = false
        }, failed = {
            isLoadingObservable.value = false
        })
    }

    override fun close() {
        coinSource.close()
    }

}