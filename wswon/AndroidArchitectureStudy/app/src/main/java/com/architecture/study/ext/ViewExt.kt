package com.architecture.study.ext

import android.view.View
import androidx.databinding.BindingAdapter
import com.architecture.study.data.model.Ticker


@BindingAdapter("item")
fun View.onItemClicked(ticker: Ticker){
    setOnClickListener {
        ticker.onClick(ticker)
    }
}