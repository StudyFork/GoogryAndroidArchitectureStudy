package com.example.studyapplication.main.image.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studyapplication.R
import com.example.studyapplication.data.model.ImageList

class ImageAdapter : RecyclerView.Adapter<ImageHolder>() {
    private val arrImageInfo : MutableList<ImageList.ImageInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_image, parent, false)

        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        arrImageInfo[position].let {
            with(holder) {
                tvTitle.text = it.title
                Glide.with(holder.itemView).load(it.link)
                    .apply(RequestOptions.centerCropTransform())
                    .into(ivImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrImageInfo.size
    }

    fun resetItem(items: Array<ImageList.ImageInfo>) {
        arrImageInfo.clear()
        arrImageInfo.addAll(items)
        notifyDataSetChanged()
    }
}