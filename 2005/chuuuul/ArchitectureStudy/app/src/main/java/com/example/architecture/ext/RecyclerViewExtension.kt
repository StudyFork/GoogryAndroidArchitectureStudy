package com.example.architecture.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture.activity.search.adapter.MovieAdapter
import com.example.architecture.data.model.MovieModel


@BindingAdapter("android:items")
fun RecyclerView.setItems(items: MutableList<MovieModel>) {
    when (this.adapter) {
        is MovieAdapter -> {
            val adapter = this.adapter as MovieAdapter
            adapter.addNewItems(items)
        }
    }
}