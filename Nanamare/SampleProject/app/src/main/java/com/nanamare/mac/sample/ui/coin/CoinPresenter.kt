package com.nanamare.mac.sample.ui.coin

import com.nanamare.mac.sample.data.coin.CoinRepository

class CoinPresenter(
    private val view: CoinContract.CoinView,
    private val ticketList: MutableList<String>
) : CoinContract.CoinPresenter {

    override fun start() {
        getCoins(ticketList)
    }

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