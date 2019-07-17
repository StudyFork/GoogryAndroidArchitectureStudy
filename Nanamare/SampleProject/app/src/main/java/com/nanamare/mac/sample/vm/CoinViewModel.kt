package com.nanamare.mac.sample.vm

import androidx.lifecycle.MutableLiveData
import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.base.BaseViewModel
import com.nanamare.mac.sample.data.coin.CoinRepository

class CoinViewModel: BaseViewModel() {

    var coins = MutableLiveData<List<CoinModel>>(mutableListOf())

    fun getCoins(ticketList: MutableList<String>) {
        isLoadingObservable.value = true
        CoinRepository.getCoins(ticketList, success = {
            coins.value = it
            isLoadingObservable.value = false
        }, failed = {
            isLoadingObservable.value = false
        })
    }

    override fun close() {
        CoinRepository.close()
    }

}