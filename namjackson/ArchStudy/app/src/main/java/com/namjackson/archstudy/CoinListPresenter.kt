package com.namjackson.archstudy

import com.namjackson.archstudy.data.repository.UpbitRepository

class CoinListPresenter(
    val upbitRepository: UpbitRepository,
    val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    override var baseCurrency: String = ""

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
        coinListView.setProgress(true)
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
                coinListView.setProgress(false)
                coinListView.showCoinList(it)
            },
            fail = { coinListView.showError("Error : $it") }
        )
    }
}