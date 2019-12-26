package com.example.kotlinapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.adapter.ListImageAdapter
import com.example.kotlinapplication.data.ImageItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_list_item.view.*

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: ImageItem, listener: ListImageAdapter.ItemListener?) {
        with(itemView){
            image_item_layout.setOnClickListener {
                listener?.let {
                    it.onImageItemClick(item)
                }
            }
            if (item.thumbnail.isNotBlank()) {
                Picasso.get()
                    .load(item.thumbnail)
                    .resize(300, 450)
                    .into(image_item_thumbnail)
            }
        }
    }
}