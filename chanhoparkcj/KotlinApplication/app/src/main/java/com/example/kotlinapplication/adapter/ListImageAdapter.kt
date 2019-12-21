package com.example.kotlinapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.ImageViewHolder
import com.example.kotlinapplication.model.ImageItems

class ListImageAdapter(
    val listener: ItemListener
) :
    RecyclerView.Adapter<ImageViewHolder>() {

    private val items = arrayListOf<ImageItems>()

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

    fun addAllItems(listItems: List<ImageItems>) {
        items.clear()
        items.addAll(listItems)
        notifyDataSetChanged()
    }

    interface ItemListener {
        fun onImageItemClick(imageItems: ImageItems)
    }


}