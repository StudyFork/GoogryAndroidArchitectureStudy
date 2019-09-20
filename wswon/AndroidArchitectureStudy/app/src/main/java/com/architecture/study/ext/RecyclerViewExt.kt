package com.architecture.study.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.data.model.Ticker
import com.architecture.study.view.coin.adapter.CoinListAdapter

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Ticker>?) {

    (this.adapter as? CoinListAdapter)?.let {
        list?.let { tickerList ->
            it.setData(tickerList)
        }
        it.notifyDataSetChanged()
    }
}