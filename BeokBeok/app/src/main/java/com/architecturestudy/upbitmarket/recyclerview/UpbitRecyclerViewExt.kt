package com.architecturestudy.upbitmarket.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setItems")
fun RecyclerView.setItems(items: List<Map<String, String>>?) {
    (this.adapter as? UpbitAdapter)?.run {
        items?.let {
            setMarketPrices(items.sortedBy { it["coinName"] })
            notifyDataSetChanged()
        }
    }
}