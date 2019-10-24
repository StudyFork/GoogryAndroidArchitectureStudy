package com.example.seonoh.seonohapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.UseCoinModel

@BindingAdapter("addCoinItem")
fun RecyclerView.addCoinData(data: List<UseCoinModel>?) {
    data?.let {
        (adapter as? CoinAdapter)?.setCoinData(data)
    }
}