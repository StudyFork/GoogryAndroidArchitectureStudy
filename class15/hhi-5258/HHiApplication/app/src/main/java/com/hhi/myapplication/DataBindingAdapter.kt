package com.hhi.myapplication

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.main.MainRecyclerAdapter
import com.hhi.myapplication.recentSearch.RecentSearchRecyclerAdapter

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<Any>?) {
    items?.let {
        when (adapter) {
            is MainRecyclerAdapter -> (adapter as MainRecyclerAdapter).setMovieList(it as ArrayList<MovieData.MovieItem>)
            is RecentSearchRecyclerAdapter -> (adapter as RecentSearchRecyclerAdapter).setQueryList(
                it as List<String>
            )
        }
    }
}