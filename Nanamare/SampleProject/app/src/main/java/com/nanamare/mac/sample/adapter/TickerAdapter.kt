package com.nanamare.mac.sample.adapter

import android.view.ViewGroup
import com.nanamare.mac.sample.adapter.viewholder.TickerViewHolder
import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.base.BaseAdapter

class TickerAdapter : BaseAdapter<TickerModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder(parent)
    }

}