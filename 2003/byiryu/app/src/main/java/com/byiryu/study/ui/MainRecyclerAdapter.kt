package com.byiryu.study.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.byiryu.study.R
import com.byiryu.study.api.model.MovieItem

class MainRecyclerAdapter :
    ListAdapter<MovieItem, MainRecyclerHolder>(object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

    }) {

    var clickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_main_item, parent, false)
        return MainRecyclerHolder(itemView, parent.context, clickListener)

    }

    override fun onBindViewHolder(holder: MainRecyclerHolder, position: Int) {
        var item = getItem(position)

        holder.binding(item)

    }

    fun setOnclickListener(clickListener: OnClickListener) {
        this.clickListener = clickListener
    }


    interface OnClickListener {
        fun onClick(url: String)
    }

}