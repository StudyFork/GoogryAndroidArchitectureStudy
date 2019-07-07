package com.architecturestudy.upbitmarket.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.architecturestudy.upbitmarket.adapter.UpbitAdapter

@BindingAdapter("setItems")
fun RecyclerView.setItems(items: List<Map<String, String>>?) {
    (this.adapter as? UpbitAdapter)?.run {
        items?.let {
            setMarketPrices(items.sortedBy { it["coinName"] })
            notifyDataSetChanged()
        }
    }
}