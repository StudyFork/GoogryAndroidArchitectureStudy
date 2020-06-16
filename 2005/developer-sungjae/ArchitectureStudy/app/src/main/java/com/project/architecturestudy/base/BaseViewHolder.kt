package com.project.architecturestudy.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.BR

open class BaseViewHolder<B : ViewDataBinding>(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {


    private val binding: B = DataBindingUtil.bind(itemView)!!

    open fun onBindViewHolder(item: Any?) {
        Log.d("BaseViewHolder", "onBindViewHolder")
        item?.let {
            with(binding) {
                setVariable(BR.movieItem, item)
                executePendingBindings()
            }
        }
    }

}