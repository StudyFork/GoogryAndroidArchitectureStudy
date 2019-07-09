package com.studyfirstproject.adapter

import android.databinding.BindingAdapter
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import org.jetbrains.anko.textColor

@BindingAdapter("bind_adapter")
fun bindAdapter(rv: RecyclerView, coinAdapter: RecyclerView.Adapter<*>) {
    rv.adapter = coinAdapter
}

@BindingAdapter("price_status_color")
fun setRateTextColor(tv: TextView, signedChangeRate: Double) {
    tv.textColor = when {
        signedChangeRate > 0 -> Color.BLUE
        signedChangeRate < 0 -> Color.RED
        else -> Color.BLACK
    }
}