package com.namjackson.archstudy.view.coinlist

import com.namjackson.archstudy.data.repository.UpbitRepository

class CoinListPresenter(
    val baseCurrency: String,
    val upbitRepository: UpbitRepository,
    val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    private lateinit var markets: String

    override fun start() {
        coinListView.presenter = this
    }

    override fun loadCoinList() {
        if (!this::markets.isInitialized) {
            initMarket()
        } else {
            getTickers(markets)
        }
    }

    fun initMarket() {
        coinListView.showProgress()
        upbitRepository.getMarketAll(
            baseCurrency,
            success = {
                markets = it
                getTickers(markets)
            },
            fail = { coinListView.showError("Error : $it") }
        )
    }

    fun getTickers(markets: String) {
        upbitRepository.getTickers(
            markets,
            success = {
                coinListView.showCoinList(it)
                coinListView.hideProgress()
            },
            fail = { coinListView.showError("Error : $it") }
        )
    }
}