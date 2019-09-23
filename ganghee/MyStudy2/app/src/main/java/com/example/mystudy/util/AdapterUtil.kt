package com.example.mystudy.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.adapter.TickerAdapter
import com.example.mystudy.data.FormatTickers

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<FormatTickers>?){
    if(list != null){
        (this.adapter as? TickerAdapter)?.setData(list)
    }
}
