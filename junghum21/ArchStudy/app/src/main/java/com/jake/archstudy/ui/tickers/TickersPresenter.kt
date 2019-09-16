package com.jake.archstudy.ui.tickers

import com.jake.archstudy.R
import com.jake.archstudy.data.source.UpbitRepository

class TickersPresenter(
    override val view: TickersContract.View,
    private val repository: UpbitRepository,
    private val marketName: String
) : TickersContract.Presenter {

    override fun onCreate() {
        getTickers()
    }

    private fun getTickers() {
        repository.getTicker(
            marketName,
            { response ->
                val tickers = response.map { it.toTicker() }
                view.setTickers(tickers)
            },
            {
                view.showToast(R.string.fail_network)
            }
        )
    }

}