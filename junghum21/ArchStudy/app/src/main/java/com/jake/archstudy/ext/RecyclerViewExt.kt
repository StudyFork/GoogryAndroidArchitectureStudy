package com.jake.archstudy.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jake.archstudy.data.model.Ticker
import com.jake.archstudy.ui.tickers.adapter.TickersAdapter

@BindingAdapter("setTickers")
fun RecyclerView.setTickers(tickers: List<Ticker>?) {
    val adapter = adapter as? TickersAdapter ?: TickersAdapter().apply { adapter = this }
    adapter.set(tickers ?: return)
    if (itemDecorationCount == 0) {
        addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    }
}