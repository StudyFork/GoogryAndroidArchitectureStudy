package com.example.studyfork.recent.adapter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studyfork.RecentSearchAdapter

@BindingAdapter("recent_bind:setItems")
fun RecyclerView.setItems(list: List<String>?) {
    list?.let { list ->
        (adapter as? RecentSearchAdapter)?.setItems(list)
    }
}