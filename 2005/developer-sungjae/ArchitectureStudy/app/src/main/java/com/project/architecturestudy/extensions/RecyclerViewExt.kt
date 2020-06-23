package com.project.architecturestudy.extensions

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.base.BaseRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("bindItem")
fun RecyclerView.bindItem(data: List<Any>?) {
    val adapter = this.adapter as BaseRecyclerViewAdapter<*, Any>
    data?.let {
        Log.d("bindItem", it.toString())
        adapter.updateData(it)
        adapter.notifyDataSetChanged()
    }
}