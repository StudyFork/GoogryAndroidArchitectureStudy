package com.architecturestudy.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.architecturestudy.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceItems")
fun RecyclerView.replaceItems(items: List<Map<String, String>>?) {
    (this.adapter as? BaseRecyclerView.BaseAdapter<Any, *>)?.run {
        replaceItems(items)
        notifyDataSetChanged()
    }
}