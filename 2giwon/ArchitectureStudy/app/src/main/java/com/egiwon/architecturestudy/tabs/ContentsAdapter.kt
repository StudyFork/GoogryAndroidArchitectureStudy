package com.egiwon.architecturestudy.tabs

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseRecyclerView
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import com.egiwon.architecturestudy.databinding.RvContentsItemBinding


class ContentsAdapter(
    private val tab: Tab,
    @LayoutRes private val layoutRes: Int = R.layout.rv_contents_item
) : BaseRecyclerView.Adapter<ContentItem, RvContentsItemBinding>(
    layoutRes
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentViewHolder =
        ContentViewHolder(parent, layoutRes).apply {
            thumbnailVisible(
                Tab.values()[viewType] == Tab.BOOK ||
                        Tab.values()[viewType] == Tab.MOVIE
            )
        }

    override fun getItemViewType(position: Int): Int = tab.ordinal
}