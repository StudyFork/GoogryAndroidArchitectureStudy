package com.egiwon.architecturestudy.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    parent: ViewGroup,
    @LayoutRes resourceId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(resourceId, parent, false)
) {
    abstract fun bind(item: T)
}
