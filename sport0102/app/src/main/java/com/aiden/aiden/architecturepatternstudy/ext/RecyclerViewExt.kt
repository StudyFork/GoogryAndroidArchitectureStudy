package com.aiden.aiden.architecturepatternstudy.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.ui.main.TickerListAdapter

@BindingAdapter("bind:item")
fun bindItem(recyclerView: RecyclerView, tickerList: List<TickerResponse>?) =
    tickerList?.let {
        (recyclerView.adapter as TickerListAdapter).apply {
            setData(tickerList)
            notifyDataSetChanged()
        }
    }

