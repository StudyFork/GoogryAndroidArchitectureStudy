package com.nanamare.mac.sample.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.malinskiy.superrecyclerview.SuperRecyclerView
import com.nanamare.mac.sample.base.BaseAdapter

@BindingAdapter("replaceAll")
fun SuperRecyclerView.replaceAll(list: List<Nothing>) {
    (adapter as BaseAdapter<*>).run {
        setList(list)
    }
}