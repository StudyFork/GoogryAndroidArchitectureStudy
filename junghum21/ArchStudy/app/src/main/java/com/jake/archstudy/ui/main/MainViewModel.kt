package com.jake.archstudy.ui.main

import androidx.databinding.ObservableField
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseViewModel
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRepository

class MainViewModel(
    private val repository: UpbitRepository
) : BaseViewModel() {

    val markets = ObservableField<List<Market>>()

    fun getMarketAll() {
        repository.getMarketAll(
            { response ->
                val markets = response.asSequence()
                    .groupBy { it.market.substringBefore("-") }
                    .map { map ->
                        val title = map.key
                        val markets = map.value.joinToString { it.market }
                        Market(title, markets)
                    }
                this.markets.set(markets)
            },
            {
                toast.set(R.string.fail_network.toString())
            }
        )
    }
}