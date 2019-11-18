package com.egiwon.architecturestudy.tabs

import android.view.ViewGroup
import android.widget.ImageView
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseContentViewHolder
import kotlinx.android.synthetic.main.rv_contents_item.view.*

class ImageContentViewHolder(
    parent: ViewGroup
) : BaseContentViewHolder(
    parent,
    R.layout.rv_contents_item
) {
    val imageThumbnail: ImageView = itemView.iv_thumbnail
}