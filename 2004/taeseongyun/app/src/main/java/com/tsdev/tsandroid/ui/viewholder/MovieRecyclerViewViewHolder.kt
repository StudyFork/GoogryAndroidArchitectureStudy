package com.tsdev.tsandroid.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BaseRecyclerViewHolder
import com.tsdev.tsandroid.databinding.MovieRecyclerItemBinding

class MovieRecyclerViewViewHolder(parent: ViewGroup) :
    BaseRecyclerViewHolder<MovieRecyclerItemBinding, Item>(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_recycler_item, parent, false)
    ) {

    override fun onBindViewHolder(item: Item?) {
        recyclerDataBinding.movieItem = item
        recyclerDataBinding.executePendingBindings()
    }
}