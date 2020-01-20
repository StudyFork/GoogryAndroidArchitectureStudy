package com.egiwon.architecturestudy.ui.tabs

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseRecyclerView
import com.egiwon.architecturestudy.databinding.RvContentItemBinding
import com.egiwon.architecturestudy.ui.Tab
import com.egiwon.data.model.ContentItem


class ContentAdapter(
    private val tab: Tab,
    @LayoutRes private val layoutRes: Int = R.layout.rv_content_item
) : BaseRecyclerView.Adapter<ContentItem, RvContentItemBinding>(
    layoutRes
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentViewHolder =
        ContentViewHolder(viewType, parent, layoutRes)

    override fun getItemViewType(position: Int): Int = tab.ordinal
}