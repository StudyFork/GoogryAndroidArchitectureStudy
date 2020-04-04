package com.byiryu.study.ui.mvp.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.byiryu.study.BR
import com.byiryu.study.R
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.wigets.OnViewClickListener

class MainRecyclerAdapter (val onViewClickListener: OnViewClickListener) :
    ListAdapter<MovieItem, MainRecyclerHolder>(object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_main_item, parent, false)

        return MainRecyclerHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainRecyclerHolder, position: Int) {
        val item = getItem(position)
        holder.viewDataBinding?.run {
            setVariable(BR.movieItem, item)
            setVariable(BR.itemClickListener, onViewClickListener)
            executePendingBindings()
        }

    }
}