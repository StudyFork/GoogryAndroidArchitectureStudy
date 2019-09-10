package com.example.architecturestudy.util

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.ui.market.RecyclerViewAdapter


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("items")
    fun RecyclerView.replaceAll(items: MutableLiveData<List<Coin>>) {
        val adapter = this.adapter as RecyclerViewAdapter
        adapter.setData(items.value)
    }
}
