package com.example.seonoh.seonohapp

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.UseCoinModel

@BindingAdapter("addCoinItem")
fun RecyclerView.addCoinData(data: List<UseCoinModel>?) {
    data?.let {
        (adapter as? CoinAdapter)?.setCoinData(data)
    }
}

@BindingAdapter("changeFormat")
fun TextView.changeStringFormat(mapValue: Map<String, Number>) {
    text = String.format(
        resources
            .getString(
                mapValue["format"]
                    .toString()
                    .toInt()
            ),
        mapValue["price"]
    )
}