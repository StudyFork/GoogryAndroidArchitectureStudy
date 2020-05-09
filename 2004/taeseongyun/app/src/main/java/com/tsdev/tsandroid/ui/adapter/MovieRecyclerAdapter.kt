package com.tsdev.tsandroid.ui.adapter

import android.util.Log
import android.view.ViewGroup
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.base.BaseRecyclerAdapter
import com.tsdev.tsandroid.base.BaseRecyclerViewHolder
import com.tsdev.tsandroid.databinding.MovieRecyclerItemBinding
import com.tsdev.tsandroid.ui.viewholder.MovieRecyclerViewViewHolder

class MovieRecyclerAdapter(private val onClickListenerEvent: MovieRecyclerViewViewHolder.OnClickDelegate) :
    BaseRecyclerAdapter<MovieRecyclerItemBinding, Item>() {

    init {
        notifiedDataChange = this@MovieRecyclerAdapter::notifyDataSetChanged
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<MovieRecyclerItemBinding, Item> =
        MovieRecyclerViewViewHolder(onClickListenerEvent, parent)

    override fun clear() {
        itemList.clear()
    }

    override fun addItem(item: Item) {
        itemList.add(item)
    }

    override fun addItems(items: List<Item>) {
        items.forEach {
            Log.d("ACTOR", it.actor)
            addItem(it)
        }
    }
}