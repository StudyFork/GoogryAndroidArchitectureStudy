package com.tsdev.tsandroid.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ITEM> : RecyclerView.Adapter<BaseRecyclerViewHolder<ITEM>>(),
    RecyclerViewModel<ITEM> {

    internal val itemList = mutableListOf<ITEM>()

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<ITEM>, position: Int) {
        holder.onBindViewHolder(itemList[position])
    }
}