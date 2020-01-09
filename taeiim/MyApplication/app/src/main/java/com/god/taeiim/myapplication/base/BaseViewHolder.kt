package com.god.taeiim.myapplication.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<B : ViewDataBinding>(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup,
    private val bindingVariableId: Int?
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    protected val binding: B = DataBindingUtil.bind(itemView)!!

    open fun onBindViewHolder(item: Any?) {
        if (bindingVariableId == null)
            return

        binding.setVariable(bindingVariableId, item)

        binding.executePendingBindings()
    }

    open fun bind(item: Any?) {

    }

}
