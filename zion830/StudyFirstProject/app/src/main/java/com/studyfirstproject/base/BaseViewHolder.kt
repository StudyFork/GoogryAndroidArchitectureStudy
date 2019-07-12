package com.studyfirstproject.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseViewHolder<out B : ViewDataBinding, T : Any>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?,
    private val bindingVariableId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutResId, parent, false)
) {
    private val binding: B = DataBindingUtil.bind(itemView)!!

    fun bind(item: T) {
        try {
            binding.run {
                setVariable(bindingVariableId, item)
                executePendingBindings()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}