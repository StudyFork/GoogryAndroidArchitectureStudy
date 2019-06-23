package com.nanamare.mac.sample.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mContext: Context = itemView.context
    private var mData: T? = null

    open fun bind(data: T) {
        this.mData = data
    }

}