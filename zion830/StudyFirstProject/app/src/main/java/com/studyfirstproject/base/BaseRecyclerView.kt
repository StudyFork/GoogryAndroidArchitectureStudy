package com.studyfirstproject.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView<B : ViewDataBinding, T : Any>(
    @LayoutRes private val layoutResId: Int,
    private val bindingVariableId: Int
) : RecyclerView.Adapter<BaseViewHolder<B, T>>() {
    protected val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : BaseViewHolder<B, T>(
            layoutResId, parent, bindingVariableId
        ) {}

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int) = items[position]

    fun setItems(data: List<T>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<B, T>, position: Int) {
        holder.bind(getItem(position))
    }
}