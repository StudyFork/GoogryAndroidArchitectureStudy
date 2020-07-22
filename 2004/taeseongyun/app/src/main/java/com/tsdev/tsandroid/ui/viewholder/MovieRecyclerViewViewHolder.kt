package com.tsdev.tsandroid.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.tsdev.domain.model.DomainItem
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BaseRecyclerViewHolder
import com.tsdev.tsandroid.databinding.MovieRecyclerItemBinding

class MovieRecyclerViewViewHolder(parent: ViewGroup) :
    BaseRecyclerViewHolder<MovieRecyclerItemBinding, DomainItem>(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_recycler_item, parent, false)
    ) {

    override fun onBindViewHolder(item: DomainItem?) {
        recyclerDataBinding.movieItem = item
        recyclerDataBinding.executePendingBindings()
    }
}