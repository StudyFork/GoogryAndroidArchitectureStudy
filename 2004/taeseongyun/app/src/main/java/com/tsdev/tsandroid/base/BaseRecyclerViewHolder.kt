package com.tsdev.tsandroid.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<BINDING: ViewDataBinding, ITEM>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {


    abstract fun onBindViewHolder(item: ITEM?)

    protected val recyclerDataBinding: BINDING = DataBindingUtil.bind(itemView)!!
}