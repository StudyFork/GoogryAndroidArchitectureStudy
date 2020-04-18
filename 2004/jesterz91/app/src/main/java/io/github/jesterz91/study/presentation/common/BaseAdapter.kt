package io.github.jesterz91.study.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<ITEM, VH : BaseViewHolder<ITEM, out ViewBinding>> :
    RecyclerView.Adapter<VH>() {

    private val items: MutableList<ITEM> = mutableListOf()

    abstract fun createViewHolder(layoutInflater: LayoutInflater): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createViewHolder(LayoutInflater.from(parent.context))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun getItem(position: Int): ITEM = items[position]

    fun addItem(item: ITEM): Boolean = items.add(item)

    fun addItem(item: List<ITEM>): Boolean = items.addAll(item)

    fun removeItem(position: Int): ITEM = items.removeAt(position)

    fun updateItem(position: Int, newItem: ITEM) {
        items[position] = newItem
    }

    fun clearItem() = items.clear()

    fun changeItems(newItems: List<ITEM>) {
        clearItem()
        addItem(newItems)
    }
}