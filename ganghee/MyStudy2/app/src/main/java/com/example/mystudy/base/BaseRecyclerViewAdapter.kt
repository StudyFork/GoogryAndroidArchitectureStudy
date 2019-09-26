package com.example.mystudy.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<ITEM : Any, B : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int,
    private val bindingId: Int? = null
) : RecyclerView.Adapter<BaseViewHolder<B>>() {

    private val items = mutableListOf<ITEM>()

    fun replaceAll(items: List<ITEM>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : BaseViewHolder<B>(
            layoutRes = layoutRes,
            parent = parent,
            bindingId = bindingId
        ) {}

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) {
        holder.onBindViewHolder(items[position])
    }
}