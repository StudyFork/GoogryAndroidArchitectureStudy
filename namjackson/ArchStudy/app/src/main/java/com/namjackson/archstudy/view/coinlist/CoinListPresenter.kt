package com.namjackson.archstudy.view.coinlist

import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.data.source.TickerRepository

class CoinListPresenter(
    var baseCurrency: String,
    val tickerRepository: TickerRepository,
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
        tickerRepository.getMarketAll(
            baseCurrency,
            success = {
                markets = it
                getTickers(markets)
            },
            fail = {
                coinListView.showError("Error : $it")
                coinListView.hideProgress()
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
                coinListView.hideProgress()
            },
            fail = {
                coinListView.showError("Error : $it")
                coinListView.hideProgress()
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
