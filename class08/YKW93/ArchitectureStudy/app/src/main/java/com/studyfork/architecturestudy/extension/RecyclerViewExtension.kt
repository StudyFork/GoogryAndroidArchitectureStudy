package com.studyfork.architecturestudy.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.ui.adapter.MovieResultRVAdapter

@BindingAdapter("setItems")
fun RecyclerView.setData(items: List<MovieResponse.Item>?) {
    if (!items.isNullOrEmpty()) {
        (adapter as MovieResultRVAdapter).setItems(items)
    }
}