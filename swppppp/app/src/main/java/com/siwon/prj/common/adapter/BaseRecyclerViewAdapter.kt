package com.siwon.prj.common.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>: RecyclerView.Adapter<BaseViewHolder<T>>(){
    private val _items: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int = _items.size
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(_items[position])
    }

    fun setItem(items: ArrayList<T>) {
        _items.clear()
        _items.addAll(items)
        notifyDataSetChanged()
    }

    fun getPositionItem(position: Int) = _items.getOrNull(position)

    // add, delete...etc

}