package com.egiwon.architecturestudy.tabs

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseViewHolder
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.ext.fromHtml
import kotlinx.android.synthetic.main.rv_contents_item.view.*

open class ContentViewHolder(
    parent: ViewGroup,
    @LayoutRes
    private val resourceId: Int = R.layout.rv_text_contents_item
) : BaseViewHolder<Content.Item>(
    parent,
    resourceId
) {
    private val tvTitle: TextView = itemView.tv_title
    private val tvDescription: TextView = itemView.tv_description
    private var linkUrl: String? = null

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

    override fun bind(item: Content.Item) {
        with(item) {
            tvDescription.text = (actor + description).fromHtml()
            tvTitle.text = title.fromHtml()
            linkUrl = link
        }
    }

}