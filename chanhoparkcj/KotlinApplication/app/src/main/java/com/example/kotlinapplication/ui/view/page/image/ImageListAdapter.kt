package com.example.kotlinapplication.ui.view.page.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.ImageItem

class ImageListAdapter(
    val listener: ItemListener
) :
    RecyclerView.Adapter<ImageViewHolder>() {

    private val items = arrayListOf<ImageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    fun addAllItems(listItems: List<ImageItem>) {
        items.clear()
        items.addAll(listItems)
        notifyDataSetChanged()
    }

    interface ItemListener {
        fun onImageItemClick(imageItems: ImageItem)
    }


}