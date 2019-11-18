package com.egiwon.architecturestudy.tabs

import android.view.ViewGroup
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseContentViewHolder
import com.egiwon.architecturestudy.data.Content

class TextContentViewHolder(
    parent: ViewGroup
) : BaseContentViewHolder(
    parent,
    R.layout.rv_text_contents_item
) {
    override fun <T> bind(item: T) {
        (item as? Content.Item)?.bind()
    }
}
