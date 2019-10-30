package com.architecture.study.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.base.BaseAdapter
import com.architecture.study.data.model.Ticker

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (adapter as? BaseAdapter<Any, *>)?.replaceAll(list)
}

