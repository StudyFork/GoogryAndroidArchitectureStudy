package com.tsdev.tsandroid.base

import androidx.recyclerview.widget.RecyclerView
import com.tsdev.tsandroid.Item

abstract class BaseRecyclerAdapter<ITEM> : RecyclerView.Adapter<BaseRecyclerViewHolder>(),
    RecyclerViewModel<ITEM> {

    internal val itemList = mutableListOf<ITEM>()

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {
        holder.onBindViewHolder(itemList[position] as Item)
    }
}