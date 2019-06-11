package com.nanamare.mac.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.adapter.viewholder.TickerViewHolder
import com.nanamare.mac.sample.api.upbit.TickerModel

class TickerAdapter(data: List<TickerModel>) : BaseAdapter<TickerModel>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false))
    }

}