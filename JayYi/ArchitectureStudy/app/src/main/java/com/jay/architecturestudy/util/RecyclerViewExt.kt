package com.jay.architecturestudy.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(items: List<Any>?) {
    (adapter as? BaseAdapter<Any, BaseViewHolder<Any>>)?.run {
        items?.let { items ->
            if (items.isEmpty()) {
                clear()
            } else {
                setData(items)
            }
        }
    }
}