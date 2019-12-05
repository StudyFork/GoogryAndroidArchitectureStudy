package com.jay.architecturestudy.ui.image

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Image
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.util.startWebView
import kotlinx.android.synthetic.main.list_item_image.view.*

internal class ImageAdapter : BaseAdapter<Image, ImageHolder>() {
    private val data = arrayListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_image, parent, false)
        return ImageHolder(view)
    }

    override fun setData(items: List<Image>) {
        super.setData(items)
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}

internal class ImageHolder(
    view: View
) : BaseViewHolder<Image>(view) {
    lateinit var item: Image

    init {
        itemView.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }

    override fun bind(item: Image) {
        this.item = item

        with(itemView) {
            image_title.text = item.title

            try {
                Glide.with(this)
                    .load(item.thumbnail)
                    .into(image_thumbnail)
            } catch (e: Exception) {
                Log.e("MovieAdapter", "error=${e.message}")
            }
        }
    }
}