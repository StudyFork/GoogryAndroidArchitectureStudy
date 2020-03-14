package com.byiryu.study.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.byiryu.study.R
import com.byiryu.study.model.data.MovieItem

class MainRecyclerAdapter :
    ListAdapter<MovieItem, MainRecyclerHolder>(object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

    }) {

    private lateinit var onClickListener: (MovieItem) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_main_item, parent, false)

        val holder = MainRecyclerHolder(itemView)

        itemView.setOnClickListener {
            onClickListener(getItem(holder.adapterPosition))
        }
        return holder

    }

    override fun onBindViewHolder(holder: MainRecyclerHolder, position: Int) {
        val item = getItem(position)

        holder.binding(item)

    }


    fun setOnclickListener(onClickListener: (MovieItem) -> Unit) {
        this.onClickListener = onClickListener
    }

}