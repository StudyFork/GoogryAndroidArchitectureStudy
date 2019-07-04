package com.aiden.aiden.architecturepatternstudy.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse


@BindingAdapter("bind:item")
fun bindItem(recyclerView: RecyclerView, tickerList: List<TickerResponse>) =
    (recyclerView.adapter as TickerListAdapter).apply {
        setData(tickerList)
        notifyDataSetChanged()
    }

