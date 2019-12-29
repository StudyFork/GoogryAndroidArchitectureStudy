package com.egiwon.architecturestudy.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class BaseViewHolder<B : ViewDataBinding>(
        parent: ViewGroup,
        @LayoutRes resourceId: Int
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(resourceId, parent, false)
    ) {
        protected val binding: B = DataBindingUtil.bind(itemView)!!

        fun onBindViewHolder(item: Any?) {
            bindItem(item)
            binding.executePendingBindings()
        }

        abstract fun bindItem(item: Any?)
    }

    abstract class Adapter<A, B : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int
    ) : RecyclerView.Adapter<BaseViewHolder<B>>() {

        private val items = mutableListOf<A>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> =
            object : BaseViewHolder<B>(parent, layoutResId) {
                override fun bindItem(item: Any?) = Unit
            }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) {
            holder.onBindViewHolder(items[position])
        }

        protected fun getItem(position: Int): A? = items.getOrNull(position)

        fun replaceAll(items: List<A>?) {
            this.items.run {
                clear()
                items?.let {
                    addAll(it)
                }
            }

            notifyDataSetChanged()
        }
    }
}