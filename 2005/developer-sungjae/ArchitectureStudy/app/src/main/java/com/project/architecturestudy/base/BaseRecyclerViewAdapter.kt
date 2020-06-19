package com.project.architecturestudy.base

import android.util.Log
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerViewAdapter<B : ViewDataBinding, ITEM>(
    @LayoutRes private val layoutResId: Int
) : RecyclerView.Adapter<BaseViewHolder<B>>() {


    private val items = mutableListOf<ITEM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> {
        return object : BaseViewHolder<B>(
            layoutResId,
            parent
        ) {}
    }

    override fun getItemCount(): Int = items.count()
    override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) {
        holder.onBindViewHolder(items[position])
    }

    protected fun getItem(position: Int): ITEM =
        items.getOrNull(position) ?: throw ArrayIndexOutOfBoundsException()

    fun updateData(items: List<ITEM>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
            }
        }
        notifyDataSetChanged()
        Log.d("itemCount", itemCount.toString())
    }


    open fun clearItems() {
        val itemCnt = items.count()
        this.items.clear()
        notifyItemRangeRemoved(0, itemCnt)
    }

}