package com.example.kotlinapplication.ui.view.page.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter(private val listener: (ImageItem) -> Unit) :
    BaseAdapter<ImageItem, ImageAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(parent, listener)

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(items[position])

    class ImageViewHolder(parent: ViewGroup, private val listener: (ImageItem) -> Unit) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent,
                false
            )
        ) {
        private lateinit var item: ImageItem

        init {
            itemView.setOnClickListener {
                listener(item)
            }
        }

        fun bind(item: ImageItem) {
            this.item = item
            with(itemView) {
                if (item.thumbnail.isNotBlank()) {
                    com.squareup.picasso.Picasso.get()
                        .load(item.thumbnail)
                        .resize(300, 450)
                        .into(imageview_image_thumbnail)
                }
            }
        }
    }
}
