package com.example.kotlinapplication.ui.view.page.image

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.data.model.ImageItem

class ImageAdapter(private val listener: (ImageItem) -> Unit) :
    RecyclerView.Adapter<ImageViewHolder>() {

    private val items = arrayListOf<ImageItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(parent, listener)

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(items[position])

    fun resetItems(listItems: List<ImageItem>) {
        items.clear()
        items.addAll(listItems)
        notifyDataSetChanged()
    }
}
