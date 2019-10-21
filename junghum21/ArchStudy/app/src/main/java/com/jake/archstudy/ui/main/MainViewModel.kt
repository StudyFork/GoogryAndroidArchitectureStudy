package com.jake.archstudy.ui.main

import androidx.databinding.ObservableField
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseViewModel
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.util.ResourceProvider

class MainViewModel(
    private val repository: UpbitRepository,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    val markets = ObservableField<List<Market>>()

    init {
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
                this.markets.set(markets)
            },
            {
                toast.set(resourceProvider.getString(R.string.fail_network))
            }
        )
    }

}