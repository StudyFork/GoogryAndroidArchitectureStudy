package com.jskim5923.architecturestudy.coin

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.model.Ticker

object CoinBindingAdapter {
    @BindingAdapter("updateItem")
    @JvmStatic
    fun RecyclerView.updateTickerList(list: List<Ticker>?) {
        list?.let {
            (adapter as? CoinListAdapter)?.updateItem(it)
        }
    }
}