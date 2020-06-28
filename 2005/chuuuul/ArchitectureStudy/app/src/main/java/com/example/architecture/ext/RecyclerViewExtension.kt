package com.example.architecture.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chul.data.model.MovieModel
import com.example.architecture.activity.search.adapter.MovieAdapter


@BindingAdapter("android:items")
fun RecyclerView.setItems(items: MutableList<MovieModel>?) {
    items?.also {
        when (this.adapter) {
            is MovieAdapter -> {
                val adapter = this.adapter as MovieAdapter
                adapter.addNewItems(it)
            }
        }
    }

}