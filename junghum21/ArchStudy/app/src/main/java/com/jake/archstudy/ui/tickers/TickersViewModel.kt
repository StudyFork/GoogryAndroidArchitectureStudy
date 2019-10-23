package com.jake.archstudy.ui.tickers

import androidx.databinding.ObservableField
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

    val tickers = ObservableField<List<Ticker>>()

    init {
        getTickers()
    }

    private fun getTickers() {
        repository.getTicker(
            marketName,
            { response ->
                val tickers = response.map { it.toTicker() }
                this.tickers.set(tickers)
            },
            {
                toast.set(resourceProvider.getString(R.string.fail_network))
            }
        )
    }

}