package io.github.jesterz91.study.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<ITEM, VH : BaseViewHolder<ITEM, out ViewBinding>>(diffCallback: DiffUtil.ItemCallback<ITEM>) :
    ListAdapter<ITEM, VH>(diffCallback) {

    abstract fun createViewHolder(layoutInflater: LayoutInflater): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createViewHolder(LayoutInflater.from(parent.context))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))
}