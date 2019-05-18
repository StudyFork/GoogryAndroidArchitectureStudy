package com.namjackson.archstudy.view.coinlist

import com.namjackson.archstudy.data.Ticker
import com.namjackson.archstudy.data.repository.UpbitRepository

class CoinListPresenter(
    var baseCurrency: String,
    val upbitRepository: UpbitRepository,
    val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    private val coinList = mutableListOf<Ticker>()
    private var searchStr: String = ""

    private lateinit var markets: String


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
                coinList.clear()
                coinList.addAll(it)

                showCoinList()
                coinListView.hideProgress()
            },
            fail = { coinListView.showError("Error : $it") }
        )
    }

    override fun changeBaseCurrency(baseCurrency: String) {
        coinListView.showProgress()
        this.baseCurrency = baseCurrency
        initMarket()
    }

    override fun search(searchStr: String) {
        this.searchStr = searchStr
        showCoinList()
    }

    fun showCoinList() {
        coinListView.showCoinList(coinList.filter { it.market.split("-")[1].contains(searchStr.toUpperCase()) })
    }
}
