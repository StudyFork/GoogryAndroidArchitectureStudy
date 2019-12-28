package com.egiwon.architecturestudy.tabs

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.architecturestudy.base.BaseRecyclerView
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import com.egiwon.architecturestudy.databinding.RvContentsItemBinding

class ContentViewHolder(
    parent: ViewGroup,
    @LayoutRes private val layoutRes: Int
) : BaseRecyclerView.BaseViewHolder<RvContentsItemBinding>(
    parent,
    layoutRes
) {

    fun setThumbnailVisible(visible: Boolean) {
        binding.ivThumbnail.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun bindItem(item: Any?) {
        (item as? ContentItem)?.let {
            binding.contentItem = it
        }
    }

}