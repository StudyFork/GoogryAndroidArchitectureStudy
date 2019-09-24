package com.example.mystudy.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?,
    private val bindingVariableId: Int?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context)
        .inflate(layoutRes, parent, false)
) {
    private val binding: B = DataBindingUtil.bind(itemView)!!

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
            itemView.visibility = View.INVISIBLE
        }
    }
}