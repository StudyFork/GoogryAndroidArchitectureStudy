package com.aiden.aiden.architecturepatternstudy.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.base.SimpleRecyclerView

@BindingAdapter("bind:item")
fun RecyclerView.bindItem(list: List<Any>?) =
    list?.let {
        (this.adapter as SimpleRecyclerView.Adapter<Any, *>).run {
            replaceAll(list)
            notifyDataSetChanged()
        }
    }


