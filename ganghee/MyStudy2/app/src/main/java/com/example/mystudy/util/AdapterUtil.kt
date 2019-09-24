package com.example.mystudy.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.base.BaseRecyclerViewAdapter

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (this.adapter as? BaseRecyclerViewAdapter<Any, *>)?.let {
        it.replaceAll(list)
        it.notifyDataSetChanged()
    }
}
