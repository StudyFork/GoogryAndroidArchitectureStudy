package com.test.androidarchitecture.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.androidarchitecture.adpter.TickerAdapter
import com.test.androidarchitecture.data.TickerFormat

@BindingAdapter("setTickerList")
fun RecyclerView.setTickerList(tickers: List<TickerFormat>?){
    val tickerAdapter =
        this.adapter as? TickerAdapter ?: TickerAdapter().also {
            this.adapter = it
        }
    tickers?.let { tickerAdapter.setItem(it) }
}