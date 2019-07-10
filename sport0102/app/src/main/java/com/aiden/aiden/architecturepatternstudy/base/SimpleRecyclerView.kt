package com.aiden.aiden.architecturepatternstudy.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleRecyclerView {

    abstract class ListAdapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutRes: Int = -1,
        private val bindingVariableId: Int? = null,
        diffCallback: DiffUtil.ItemCallback<ITEM>
    ) : androidx.recyclerview.widget.ListAdapter<ITEM, ViewHolder<B>>(
        diffCallback
    ) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : ViewHolder<B>(
                layoutRes = layoutRes,
                parent = parent,
                bindingVariableId = bindingVariableId
            ) {}

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            holder.onBindViewHolder(getItem(position))
        }
    }

    abstract class Adapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutRes: Int = -1,
        private val bindingVariableId: Int? = null
    ) : RecyclerView.Adapter<ViewHolder<B>>() {

        protected val items = mutableListOf<ITEM>()

        fun replaceAll(items: List<ITEM>?) {
            items?.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : ViewHolder<B>(
                layoutRes = layoutRes,
                parent = parent,
                bindingVariableId = bindingVariableId
            ) {}

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            holder.onBindViewHolder(items[position])
        }
    }

    abstract class ViewHolder<out B : ViewDataBinding>(
        @LayoutRes layoutRes: Int,
        parent: ViewGroup?,
        private val bindingVariableId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent?.context)
            .inflate(layoutRes, parent, false)
    ) {

        val binding: B = DataBindingUtil.bind(itemView)!!

        fun onBindViewHolder(item: Any?) {
            try {
                binding.run {
                    bindingVariableId?.let {
                        setVariable(it, item)
                    }
                    executePendingBindings()
                }
                itemView.visibility = View.VISIBLE
            } catch (e: Exception) {
                e.printStackTrace()
                itemView.visibility = View.GONE
            }
        }
    }

}