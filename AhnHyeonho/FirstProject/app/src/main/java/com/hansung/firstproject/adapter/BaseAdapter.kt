package com.hansung.firstproject.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, B : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val items: ArrayList<T> = arrayListOf()

    fun addItems(_item: ArrayList<T>) {
        clearItems()
        items.addAll(_item)
    }

    private fun clearItems() {
        items.clear()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bindItems(item = items[position])
}