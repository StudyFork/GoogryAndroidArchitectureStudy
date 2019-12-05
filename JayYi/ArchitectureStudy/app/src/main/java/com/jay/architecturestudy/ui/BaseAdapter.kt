package com.jay.architecturestudy.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, H : BaseViewHolder<T>>(
) : RecyclerView.Adapter<H>() {
    private val items: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =
        items.size

    open fun setData(items: List<T>) {
        this.items.addAll(items)
    }
}


abstract class BaseViewHolder<T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}
