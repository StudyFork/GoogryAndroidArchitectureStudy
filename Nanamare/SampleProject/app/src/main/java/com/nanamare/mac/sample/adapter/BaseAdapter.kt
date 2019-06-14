package com.nanamare.mac.sample.adapter


import androidx.recyclerview.widget.RecyclerView
import com.nanamare.mac.sample.adapter.viewholder.BaseViewHolder


abstract class BaseAdapter<T : Any>(data: List<T>) : RecyclerView.Adapter<BaseViewHolder<Any>>() {

    private var mData: MutableList<T> = data.toMutableList()

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setList(data: List<T>) {
        clear()
        addAll(data)
    }


    fun add(data: T) {
        insert(data, mData.size)
    }


    fun addAll(data: List<T>?) {
        if (data == null) {
            return
        }

        val startIndex = mData.size
        mData.addAll(startIndex, data)
        notifyItemRangeInserted(startIndex, data.size)
    }

    fun getItem(position: Int): T {
        return mData[position]
    }


    fun insert(data: T, position: Int) {
        mData.add(position, data)
        notifyItemInserted(position)
    }


    fun remove(position: Int) {
        mData.removeAt(position)
        notifyItemRemoved(position)
    }


    fun change(position: Int, data: T) {
        mData[position] = data
        notifyItemChanged(position)
    }

    fun clear() {
        val size = mData.size
        mData.clear()
        notifyItemRangeRemoved(0, size)
    }
}
