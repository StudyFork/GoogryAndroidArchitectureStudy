package com.jake.archstudy.ui.tickers

import androidx.databinding.ObservableField
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseViewModel
import com.jake.archstudy.data.model.Ticker
import com.jake.archstudy.data.source.UpbitRepository

class TickersViewModel(
    private val repository: UpbitRepository,
    private val marketName: String
) : BaseViewModel() {

    val tickers = ObservableField<List<Ticker>>()

    fun getTickers() {
        repository.getTicker(
            marketName,
            { response ->
                val tickers = response.map { it.toTicker() }
                this.tickers.set(tickers)
            },
            {
                toast.set(R.string.fail_network.toString())
            }
        )
    }

}