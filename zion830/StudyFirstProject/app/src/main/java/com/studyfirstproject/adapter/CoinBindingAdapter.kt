package com.studyfirstproject.adapter

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfirstproject.data.model.TickerModel

@BindingAdapter("bind_item")
fun bindTickerItems(rv: RecyclerView, data: List<TickerModel>) {
    val adapter = rv.adapter as CoinRecyclerViewAdapter
    adapter.setCoinList(data)
}

@BindingAdapter("price_status_color")
fun setPriceStatusColor(tv: TextView, signedChangeRate: Double) {
    tv.setTextColor(
        when {
            signedChangeRate > 0 -> Color.BLUE
            signedChangeRate < 0 -> Color.RED
            else -> Color.BLACK
        }
    )
}