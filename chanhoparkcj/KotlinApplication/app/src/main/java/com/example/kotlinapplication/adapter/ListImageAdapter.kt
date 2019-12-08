package com.example.kotlinapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.ImageViewHolder
import com.example.kotlinapplication.model.ImageItems

class ListImageAdapter(
    listener: ItemListener
) :
    RecyclerView.Adapter<ImageViewHolder>() {

    private lateinit var items: List<ImageItems>
    private var mListener: ItemListener = listener

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

        holder.bind(items.get(position), mListener)

    }

    fun addAllItems(listItems: List<ImageItems>) {
        items = listItems
    }

    interface ItemListener {
        fun onImageItemClick(imageItems: ImageItems)
    }


}