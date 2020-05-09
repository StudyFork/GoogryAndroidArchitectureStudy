package io.github.jesterz91.study.presentation.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<ITEM, B : ViewBinding>(val binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: ITEM)
}