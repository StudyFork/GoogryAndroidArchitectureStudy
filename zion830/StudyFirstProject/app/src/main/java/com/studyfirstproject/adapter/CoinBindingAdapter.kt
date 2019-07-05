package com.studyfirstproject.adapter

import android.databinding.BindingAdapter
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import org.jetbrains.anko.textColor

object CoinBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind_adapter")
    fun bindAdapter(rv: RecyclerView, coinAdapter: RecyclerView.Adapter<*>) {
        rv.adapter = coinAdapter
    }

    @JvmStatic
    @BindingAdapter("text_color")
    fun setRateTextColor(tv: TextView, signedChangeRate: Double) {
        tv.textColor = when {
            signedChangeRate > 0 -> Color.BLUE
            signedChangeRate < 0 -> Color.RED
            else -> Color.BLACK
        }
    }
}