package com.tsdev.tsandroid.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<ITEM : Any>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun initBind(item: ITEM)
}

typealias OnClickPositionEvent = (Int) -> Unit