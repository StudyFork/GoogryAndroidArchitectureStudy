package com.tsdev.tsandroid.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BaseRecyclerViewHolder
import com.tsdev.tsandroid.databinding.MovieRecyclerItemBinding
import com.tsdev.tsandroid.ext.showToast

class MovieRecyclerViewViewHolder(onClickListenerEvent: OnClickDelegate, parent: ViewGroup) :
    BaseRecyclerViewHolder<MovieRecyclerItemBinding, Item>(
        LayoutInflater.from(parent.context).inflate(R.layout.movie_recycler_item, parent, false)
    ) {
    init {
        recyclerDataBinding.root.setOnClickListener {
            adapterPosition.takeIf { adapterPosition > -1 }?.let {
                onClickListenerEvent.onClickEventListener(it)
            } ?: parent.context.showToast(Toast.LENGTH_LONG)
        }
    }

    interface OnClickDelegate {
        fun onClickEventListener(position: Int)
    }

    override fun onBindViewHolder(item: Item) {
        recyclerDataBinding.movieItem = item
        recyclerDataBinding.executePendingBindings()
    }
}