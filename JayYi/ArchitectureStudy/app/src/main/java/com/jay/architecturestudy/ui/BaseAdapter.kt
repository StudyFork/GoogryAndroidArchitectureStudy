package com.jay.architecturestudy.ui

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, H : BaseViewHolder<T>> : RecyclerView.Adapter<H>() {
    private val items: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =
        items.size

    fun setData(items: List<T>) {
        clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}


abstract class BaseViewHolder<T>(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}

interface OnItemClickListener {
    fun onClick(v: View, url: String)
}
