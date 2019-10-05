package com.jake.archstudy.ui.main

import com.jake.archstudy.R
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRepository

class MainPresenter(
    override val view: MainContract.View,
    private val repository: UpbitRepository
) : MainContract.Presenter {

    override fun start() {
        getMarketAll()
    }

    private fun getMarketAll() {
        repository.getMarketAll(
            { response ->
                val markets = response.asSequence()
                    .groupBy { it.market.substringBefore("-") }
                    .map { map ->
                        val title = map.key
                        val markets = map.value.joinToString { it.market }
                        Market(title, markets)
                    }
                view.setViewPager(markets)
            },
            {
                view.showToast(R.string.fail_network)
            }
        )
    }


}