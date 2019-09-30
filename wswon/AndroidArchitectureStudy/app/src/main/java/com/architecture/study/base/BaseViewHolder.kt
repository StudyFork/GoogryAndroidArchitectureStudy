package com.architecture.study.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(
    @LayoutRes
    layoutRes: Int,
    parent: ViewGroup?,
    private val dataBindingId: Int?
) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent?.context)
        .inflate(layoutRes, parent, false)
) {
    val binding: B? = DataBindingUtil.bind(itemView)

    fun bind(item: Any?) {
        if (item != null) {
            binding?.run {
                dataBindingId?.let {
                    setVariable(it, item)
                }
                executePendingBindings()
            }
            itemView.visibility = View.VISIBLE
        } else {
            itemView.visibility = View.GONE
        }
    }
}