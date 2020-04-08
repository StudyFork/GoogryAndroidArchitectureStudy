package com.tsdev.tsandroid.base

import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

abstract class BaseRecyclerAdapter<ITEM> : RecyclerView.Adapter<BaseRecyclerViewHolder<*>>(),
    RecyclerViewModel {

    internal val itemList = mutableListOf<ITEM>()

    override fun getItemCount(): Int = itemList.size
}