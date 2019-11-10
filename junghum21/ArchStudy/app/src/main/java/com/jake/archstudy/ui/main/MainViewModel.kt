package com.jake.archstudy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseViewModel
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.util.ResourceProvider

class MainViewModel(
    private val repository: UpbitRepository,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private val _markets = MutableLiveData<List<Market>>()
    val markets: LiveData<List<Market>> = _markets

    init {
        getMarketAll()
    }

    private fun getMarketAll() {
        repository.getMarketAll(
            success = { response ->
                val markets = response.asSequence()
                    .groupBy { it.market.substringBefore("-") }
                    .map { map ->
                        val (title, market) = map.key to map.value.joinToString { it.market }
                        Market(title, market)
                    }
                _markets.value = markets
            },
            failure = {
                toast(resourceProvider.getString(R.string.fail_network))
            }
        )
    }

}