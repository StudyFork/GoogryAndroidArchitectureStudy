package com.nanamare.mac.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.adapter.viewholder.TickerViewHolder
import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.base.BaseAdapter

class TickerAdapter(data: List<TickerModel>) : BaseAdapter<TickerModel>(data) {
    constructor() : this(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false))
    }

}