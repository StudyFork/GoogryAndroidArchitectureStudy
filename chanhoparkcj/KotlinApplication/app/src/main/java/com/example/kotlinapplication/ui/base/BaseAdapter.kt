package com.example.kotlinapplication.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, S : RecyclerView.ViewHolder?> : RecyclerView.Adapter<S>() {
    val items = mutableListOf<T>()
    override fun getItemCount(): Int = items.size

    fun resetItems(ListItems: List<T>) {
        items.clear()
        items.addAll(ListItems)
        notifyDataSetChanged()
    }

    fun removeAll() {
        items.clear()
        notifyDataSetChanged()
    }

}