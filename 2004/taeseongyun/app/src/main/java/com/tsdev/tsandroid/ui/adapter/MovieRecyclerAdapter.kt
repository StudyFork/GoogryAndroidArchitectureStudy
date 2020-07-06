package com.tsdev.tsandroid.ui.adapter

import android.view.ViewGroup
import com.tsdev.data.model.EntryItem
import com.tsdev.domain.model.DomainItem
import com.tsdev.tsandroid.base.BaseRecyclerAdapter
import com.tsdev.tsandroid.base.BaseRecyclerViewHolder
import com.tsdev.tsandroid.databinding.MovieRecyclerItemBinding
import com.tsdev.tsandroid.ui.viewholder.MovieRecyclerViewViewHolder

class MovieRecyclerAdapter :
    BaseRecyclerAdapter<MovieRecyclerItemBinding, DomainItem>() {

    init {
        notifiedDataChange = this@MovieRecyclerAdapter::notifyDataSetChanged
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<MovieRecyclerItemBinding, DomainItem> =
        MovieRecyclerViewViewHolder(parent)

    override fun clear() {
        itemList.clear()
    }

    override fun addItem(item: DomainItem) {
        itemList.add(item)
    }

    override fun addItems(items: List<DomainItem>?) {
        items?.forEach {
            addItem(it)
        }
    }
}