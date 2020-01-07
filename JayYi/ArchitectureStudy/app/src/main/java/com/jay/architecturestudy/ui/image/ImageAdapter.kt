package com.jay.architecturestudy.ui.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.databinding.ListItemImageBinding
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.util.startWebView

internal class ImageAdapter : BaseAdapter<Image, ImageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = DataBindingUtil.inflate<ListItemImageBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_image,
            parent,
            false
        )
        return ImageHolder(binding)
    }

}

internal class ImageHolder(
    val binding: ListItemImageBinding
) : BaseViewHolder<Image>(binding) {
    lateinit var item: Image

    init {
        itemView.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }

    override fun bind(item: Image) {
        this.item = item

        with(binding) {
            title = item.title
            imageUrl = item.thumbnail
            imageThumbnail
            executePendingBindings()
        }
    }
}