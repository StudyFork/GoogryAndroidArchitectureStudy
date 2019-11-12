package com.egiwon.architecturestudy.tabs

import android.view.ViewGroup
import android.widget.ImageView
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseViewHolder
import kotlinx.android.synthetic.main.rv_contents_item.view.*

class ImageViewHolder(
    parent: ViewGroup
) : BaseViewHolder(
    parent,
    R.layout.rv_contents_item
) {
    val imageThumbnail: ImageView = itemView.iv_thumbnail
}