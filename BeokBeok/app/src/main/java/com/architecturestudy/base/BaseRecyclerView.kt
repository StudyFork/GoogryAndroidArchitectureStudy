package com.architecturestudy.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class BaseViewHolder<VDB : ViewDataBinding>(
        @LayoutRes
        private val layoutRes: Int,
        parent: ViewGroup,
        private val bindingVariableId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                layoutRes,
                parent,
                false
            )
    ) {
        private val binding: VDB = DataBindingUtil.bind(itemView)!!

        fun onBind(item: Any?) {
            bindingVariableId?.let {
                binding.setVariable(
                    it,
                    item
                )
            }
        }
    }

    abstract class BaseAdapter<A : Any, VDB : ViewDataBinding>(
        @LayoutRes
        private val layoutRes: Int,
        private val bindingVariableId: Int?
    ) : RecyclerView.Adapter<BaseViewHolder<VDB>>() {
        private val items = mutableListOf<A>()

        fun replaceItems(items: List<A>?) {
            items?.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<VDB> =
            object : BaseViewHolder<VDB>(
                layoutRes,
                parent,
                bindingVariableId
            ) {}

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(
            holder: BaseViewHolder<VDB>,
            position: Int
        ) {
            holder.onBind(items[position])
        }

    }
}