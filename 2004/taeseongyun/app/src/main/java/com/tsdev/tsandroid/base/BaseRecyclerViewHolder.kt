package com.tsdev.tsandroid.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tsdev.tsandroid.Item
import java.lang.Exception

abstract class BaseRecyclerViewHolder<ITEM>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {


    abstract fun onBindViewHolder(item: ITEM)
}