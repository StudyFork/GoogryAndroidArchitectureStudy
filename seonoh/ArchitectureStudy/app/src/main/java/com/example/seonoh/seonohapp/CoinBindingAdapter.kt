package com.example.seonoh.seonohapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.UseCoinModel

object CoinBindingAdapter{
    @BindingAdapter("addCoinItem")
    @JvmStatic
    fun RecyclerView.addCoinData(data : List<UseCoinModel>?){
        data?.let {
            (adapter as? CoinAdapter)?.setCoinData(data)
        }
    }
}