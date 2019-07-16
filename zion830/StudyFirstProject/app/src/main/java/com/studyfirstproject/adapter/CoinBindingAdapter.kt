package com.studyfirstproject.adapter

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfirstproject.base.BaseRecyclerView
import com.studyfirstproject.data.model.TickerModel

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind_item")
fun bindItems(rv: RecyclerView, data: List<TickerModel>) {
    (rv.adapter as BaseRecyclerView<*, Any>).setItems(data)
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