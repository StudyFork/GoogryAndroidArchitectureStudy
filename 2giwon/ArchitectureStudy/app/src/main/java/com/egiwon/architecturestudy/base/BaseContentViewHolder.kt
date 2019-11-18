package com.egiwon.architecturestudy.base

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_contents_item.view.*

abstract class BaseContentViewHolder(
    parent: ViewGroup,
    @LayoutRes
    private val resourceId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
) {
    val tvTitle: TextView = itemView.tv_title
    val tvDescription: TextView = itemView.tv_description
    var linkUrl: String? = null

}