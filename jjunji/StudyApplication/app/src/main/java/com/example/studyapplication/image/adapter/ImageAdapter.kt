package com.example.studyapplication.image.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studyapplication.R
import com.example.studyapplication.vo.ImageList
import kotlinx.android.synthetic.main.item_blog.view.tvTitle
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter (var arrImageInfo : Array<ImageList.ImageInfo>) : RecyclerView.Adapter<ImageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = inflater.inflate(R.layout.item_image, parent, false)

        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.itemView.tvTitle.text = arrImageInfo[position].title

        Glide.with(holder.itemView).load(arrImageInfo[position].link)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.itemView.ivImage)
    }

    override fun getItemCount(): Int {
        return arrImageInfo.size
    }

}