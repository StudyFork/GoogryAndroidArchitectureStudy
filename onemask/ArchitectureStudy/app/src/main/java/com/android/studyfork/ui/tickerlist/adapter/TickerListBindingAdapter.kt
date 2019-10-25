package com.android.studyfork.ui.tickerlist.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.studyfork.network.model.Ticker
import com.android.studyfork.ui.adapter.CoinItemAdapter

/**
 * created by onemask
 */
object TickerListBindingAdapter {
    @BindingAdapter("updateItem")
    @JvmStatic
    fun RecyclerView.updateTickerList(list :List<Ticker>?) {
        list?.let {
            (adapter as? CoinItemAdapter)?.setData(it)
        }
    }
}