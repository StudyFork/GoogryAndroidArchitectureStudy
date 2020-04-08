package com.tsdev.tsandroid.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

abstract class BaseRecyclerViewHolder<ITEM>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bindingCheckItem(item: Any) {
        try {
            onBindViewHolder(item as ITEM)
        } catch (e: Exception) {
        }
    }

    abstract fun onBindViewHolder(item: ITEM)
}