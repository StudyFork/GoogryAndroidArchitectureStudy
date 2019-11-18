package com.egiwon.architecturestudy.base

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.ext.fromHtml
import kotlinx.android.synthetic.main.rv_contents_item.view.*

abstract class BaseContentViewHolder(
    parent: ViewGroup,
    @LayoutRes
    private val resourceId: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
) {
    private val tvTitle: TextView = itemView.tv_title
    private val tvDescription: TextView = itemView.tv_description
    private var linkUrl: String? = null

    abstract fun <T> bind(item: T)

    protected fun Content.Item.bind() {
        tvDescription.text = description.fromHtml()
        tvTitle.text = (actor + title).fromHtml()
        linkUrl = link
    }

    init {
        itemView.setOnClickListener {
            linkUrl?.let { url ->
                ContextCompat.startActivity(
                    it.context,
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(url)
                    ),
                    null
                )
            }
        }
    }
}