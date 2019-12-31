package com.example.kotlinapplication.ui.view.page.image

import android.view.ViewGroup
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.ui.base.BaseAdapter

class ImageAdapter(private val listener: (ImageItem) -> Unit) :
    BaseAdapter<ImageItem, ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(parent, listener)

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(items[position])

}
