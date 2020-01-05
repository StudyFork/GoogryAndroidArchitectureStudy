package com.god.taeiim.myapplication.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class BaseRecyclerAdapter<ITEM, B : ViewDataBinding>(
    @LayoutRes val layoutResId: Int,
    val bindingVariableId: Int? = null
) : RecyclerView.Adapter<BaseViewHolder<B>>() {

    private val items = mutableListOf<ITEM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> =
        object : BaseViewHolder<B>(
            layoutResId,
            parent,
            bindingVariableId
        ) {}

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) =
        holder.onBindViewHolder(items[position])

    protected fun getItem(position: Int): ITEM? = items.getOrNull(position)

    fun updateItems(items: List<ITEM>?) {
        this.items.run {
            clear()
            items?.let {
                addAll(it)
            }
        }

        notifyDataSetChanged()
    }

    open fun clearItems() {
        val size = items.size
        this.items.clear()
        notifyItemRangeRemoved(0, size)
    }
}