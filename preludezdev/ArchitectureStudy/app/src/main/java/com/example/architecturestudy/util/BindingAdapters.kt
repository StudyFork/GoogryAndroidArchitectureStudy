package com.example.architecturestudy.util

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.ui.market.RecyclerViewAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("items")
    fun RecyclerView.replaceAll(items: ObservableField<List<Coin>>) {
        val adapter = this.adapter

        if (adapter is RecyclerViewAdapter)
            adapter.setData(items.get())
    }
}
