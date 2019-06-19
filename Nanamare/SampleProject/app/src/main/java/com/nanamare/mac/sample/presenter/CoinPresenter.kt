package com.nanamare.mac.sample.presenter

import com.nanamare.mac.sample.contract.CoinContract
import com.nanamare.mac.sample.data.CoinRepository

class CoinPresenter(private val view: CoinContract.CoinView) : CoinContract.CoinPresenter {

    override fun getCoins(ticketList: MutableList<String>) {
        CoinRepository.getCoins(ticketList, success = {
            view.showCoins(it)
        }, failed = {
            view.hideLoadingDialog()
        })
    }

    override fun close() {
        CoinRepository.close()
    }

}