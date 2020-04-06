package com.tsdev.tsandroid.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ITEM> : RecyclerView.Adapter<BaseRecyclerViewHolder<*>>(), RecyclerViewModel {

    internal val movieList = mutableListOf<ITEM>()

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<*>, position: Int) {
        createBindViewHolder(holder, position)
    }

    abstract fun createBindViewHolder(holder: BaseRecyclerViewHolder<*>, position: Int)
}