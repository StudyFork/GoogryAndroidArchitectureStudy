package com.example.architecturestudy.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.ui.market.RecyclerViewAdapter

@BindingAdapter("items")
fun RecyclerView.replaceAll(items: List<Coin>?) {
    (adapter as? RecyclerViewAdapter)?.setData(items)
}

