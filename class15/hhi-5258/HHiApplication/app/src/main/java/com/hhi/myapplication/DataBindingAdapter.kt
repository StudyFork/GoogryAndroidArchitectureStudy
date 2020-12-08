package com.hhi.myapplication

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.main.MainRecyclerAdapter
import com.hhi.myapplication.recentSearch.RecentSearchRecyclerAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<Any>?) {
    items?.let {
        when (val adapter = adapter) {
            is MainRecyclerAdapter -> adapter.setMovieList(it as ArrayList<MovieData.MovieItem>)
            is RecentSearchRecyclerAdapter -> adapter.setQueryList(
                it as List<String>
            )
        }
    }
}