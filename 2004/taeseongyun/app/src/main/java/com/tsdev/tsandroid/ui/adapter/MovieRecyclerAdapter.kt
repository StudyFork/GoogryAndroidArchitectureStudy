package com.tsdev.tsandroid.ui.adapter

import android.view.ViewGroup
import com.tsdev.tsandroid.Item
import com.tsdev.tsandroid.base.BaseRecyclerAdapter
import com.tsdev.tsandroid.base.BaseRecyclerViewHolder
import com.tsdev.tsandroid.base.OnClickPositionEvent
import com.tsdev.tsandroid.ui.viewholder.MovieRecyclerViewViewHolder

class MovieRecyclerAdapter(private val onClickListenerEvent: MovieRecyclerViewViewHolder.OnClickDelegate) :
    BaseRecyclerAdapter<Item>() {
    override fun createBindViewHolder(holder: BaseRecyclerViewHolder<*>, position: Int) {
        when (holder) {
            is MovieRecyclerViewViewHolder -> holder.initBind(movieList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<*> =
        MovieRecyclerViewViewHolder(onClickListenerEvent, parent)


    override fun addItem(item: Any) {
        movieList.add(item as Item)
    }

    override fun addItems(items: List<Any>) {
        items.forEach {
            addItem(it)
        }
    }

    override fun clear() {
        movieList.clear()
    }
}