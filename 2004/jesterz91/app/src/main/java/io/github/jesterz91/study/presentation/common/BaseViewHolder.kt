package io.github.jesterz91.study.presentation.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide

abstract class BaseViewHolder<ITEM, B : ViewBinding>(val binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    protected val glide by lazy { Glide.with(itemView.context) }

    abstract fun bind(item: ITEM)

}