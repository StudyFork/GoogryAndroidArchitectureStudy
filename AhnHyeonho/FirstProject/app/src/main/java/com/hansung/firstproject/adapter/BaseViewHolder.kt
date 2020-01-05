package com.hansung.firstproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(@LayoutRes layoutId: Int, parents: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parents.context).inflate(layoutId, parents, false)
    ) {
    abstract fun bindItems(item: T)
}
