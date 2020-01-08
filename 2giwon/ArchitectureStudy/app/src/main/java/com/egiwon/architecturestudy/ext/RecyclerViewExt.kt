package com.egiwon.architecturestudy.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.architecturestudy.base.BaseRecyclerView

@BindingAdapter("setHasFixedSize")
fun RecyclerView.bindHasFixedSize(fixedSize: Boolean) = setHasFixedSize(fixedSize)

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(items: List<Any>?) {
    (this.adapter as? BaseRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(items)
        notifyDataSetChanged()
    }
}