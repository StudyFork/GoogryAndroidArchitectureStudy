package com.nanamare.mac.sample.adapter.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<out T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mContext: Context = itemView.context
    private lateinit var mData: Any

    open fun bind(data: Any) {
        this.mData = data
    }

}