package com.jake.archstudy.ui.tickers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseViewModel
import com.jake.archstudy.data.model.Ticker
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.util.ResourceProvider

class TickersViewModel(
    private val repository: UpbitRepository,
    private val marketName: String,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private val _tickers = MutableLiveData<List<Ticker>>()
    val tickers: LiveData<List<Ticker>> = _tickers

    init {
        getTickers()
    }

    private fun getTickers() {
        repository.getTicker(
            markets = marketName,
            success = { response ->
                _tickers.value = response.map { it.toTicker() }
            },
            failure = {
                _toast.value = resourceProvider.getString(R.string.fail_network)
            }
        )
    }

}