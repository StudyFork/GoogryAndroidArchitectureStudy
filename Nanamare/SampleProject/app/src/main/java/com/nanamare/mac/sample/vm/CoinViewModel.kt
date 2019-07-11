package com.nanamare.mac.sample.vm

import androidx.databinding.ObservableField
import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.data.coin.CoinRepository
import com.nanamare.mac.sample.ui.coin.CoinNavigator

class CoinViewModel {

    var coins = ObservableField<List<CoinModel>>(mutableListOf())

    var navigator: CoinNavigator? = null

    fun getCoins(ticketList: MutableList<String>) {
        CoinRepository.getCoins(ticketList, success = {
            coins.set(it)
        }, failed = {
            navigator?.hideLoadingDialog()
        })
    }

    fun onClose() {
        CoinRepository.close()
    }

}