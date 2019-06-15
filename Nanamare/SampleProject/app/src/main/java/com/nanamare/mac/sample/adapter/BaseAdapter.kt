package com.nanamare.mac.sample.adapter


import androidx.recyclerview.widget.RecyclerView
import com.nanamare.mac.sample.adapter.viewholder.BaseViewHolder


abstract class BaseAdapter<T>(data: List<T>) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var items: MutableList<T> = data.toMutableList()

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(data: List<T>) {
        clear()
        addAll(data)
    }


    fun add(data: T) {
        insert(data, items.size)
    }


    fun addAll(data: List<T>?) {
        if (data == null) {
            return
        }

        val startIndex = items.size
        items.addAll(startIndex, data)
        notifyItemRangeInserted(startIndex, data.size)
    }

    fun getItem(position: Int): T {
        return items[position]
    }


    fun insert(data: T, position: Int) {
        items.add(position, data)
        notifyItemInserted(position)
    }


    fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }


    fun change(position: Int, data: T) {
        items[position] = data
        notifyItemChanged(position)
    }

    fun clear() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }
}
