package com.egiwon.architecturestudy.tabs

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseRecyclerView
import com.egiwon.architecturestudy.data.source.remote.response.ContentItem
import com.egiwon.architecturestudy.databinding.RvContentsItemBinding
import com.egiwon.architecturestudy.ext.fromHtml
import com.egiwon.architecturestudy.ext.loadUrl

class ContentViewHolder(
    parent: ViewGroup,
    @LayoutRes private val layoutRes: Int
) : BaseRecyclerView.BaseViewHolder<RvContentsItemBinding>(
    parent,
    layoutRes
) {
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

    override fun onBindViewHolder(item: Any?) {
        (item as? ContentItem)?.let {
            with(item) {
                binding.tvDescription.text = (actor + description).fromHtml()
                binding.tvTitle.text = title.fromHtml()
                binding.ivThumbnail.loadUrl(image) {
                    placeholder(R.mipmap.ic_launcher)
                }
                linkUrl = link
            }
        }
    }

    fun thumbnailVisible(visible: Boolean) {
        binding.ivThumbnail.visibility = if (visible) View.VISIBLE else View.GONE
    }

}