package com.byiryu.study.ui.mvvm.main.views.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
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

    lateinit var lifecycleOwner: LifecycleOwner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_main_item, parent, false)

        return MainRecyclerHolder(itemView, lifecycleOwner, onViewClickListener)
    }

    override fun onBindViewHolder(holder: MainRecyclerHolder, position: Int) {
        holder.bind(getItem(position))
    }
}