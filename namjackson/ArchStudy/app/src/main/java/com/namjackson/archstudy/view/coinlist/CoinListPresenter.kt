package com.namjackson.archstudy.view.coinlist

import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.data.source.TickerRepository

class CoinListPresenter(
    val tickerRepository: TickerRepository,
    val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    private var baseCurrency: String=""
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
        coinListView.showLoading()
        tickerRepository.getMarketAll(
            baseCurrency,
            success = {
                markets = it
                getTickers(markets)
            },
            fail = {
                coinListView.showError("Error : $it")
                coinListView.hideLoading()
            }
        )
    }

    fun getTickers(markets: String) {
        tickerRepository.getTickers(
            markets,
            success = {
                coinList.clear()
                coinList.addAll(it)

                showCoinList()
                coinListView.hideLoading()
            },
            fail = {
                coinListView.showError("Error : $it")
                coinListView.hideLoading()
            }
        )
    }

    override fun changeBaseCurrency(baseCurrency: String) {
        this.baseCurrency = baseCurrency
        initMarket()
    }

    override fun search(searchStr: String) {
        this.searchStr = searchStr
        showCoinList()
    }

    private fun showCoinList() {
        coinListView.showCoinList(coinList.filter { it.name.contains(searchStr.toUpperCase()) })
    }
}
