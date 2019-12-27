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
    override fun onBindViewHolder(item: Any?) {
        (item as? ContentItem)?.let {
            with(item) {
                binding.description = (actor + description)
                binding.title = title
                binding.image = image
                binding.url = link
            }
        }
        super.onBindViewHolder(item)
    }

    fun thumbnailVisible(visible: Boolean) {
        binding.ivThumbnail.visibility = if (visible) View.VISIBLE else View.GONE
    }

}