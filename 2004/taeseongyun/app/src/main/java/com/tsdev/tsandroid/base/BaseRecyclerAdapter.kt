package com.tsdev.tsandroid.base

import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<BINDING : ViewDataBinding, ITEM> :
    RecyclerView.Adapter<BaseRecyclerViewHolder<BINDING, ITEM>>(),
    RecyclerViewModel<ITEM> {

    internal val itemList = mutableListOf<ObservableField<ITEM>>()

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<BINDING, ITEM>, position: Int) {
        holder.onBindViewHolder(itemList[position].get())
    }

    override lateinit var notifiedDataChange: () -> Unit
}