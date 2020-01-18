package com.siwon.prj.common.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: T)
}