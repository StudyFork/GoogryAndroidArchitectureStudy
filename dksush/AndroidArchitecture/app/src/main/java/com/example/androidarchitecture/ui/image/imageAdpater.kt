package com.example.androidarchitecture.ui.image

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseImage
import kotlinx.android.synthetic.main.item_image.view.*

class imageAdpater (
    val items:List<ResponseImage>,
    val mContext: Context,
    val mOnItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<imageAdpater.ImageHolder>() {


    interface OnItemClickListener {
        fun onItemClick(link: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val Image_item = items.get(position)
        holder.itemView.image_title.text = Image_item.title

        if (Image_item.thumbnail != null) {
            Glide.with(mContext)
                .load(Image_item.thumbnail)
                .into(holder.itemView.image_thum)
        }

        holder.itemView.setOnClickListener {
            mOnItemClickListener.let { it.onItemClick(Image_item.link) }
        }
    }


    inner class ImageHolder(view: View) : RecyclerView.ViewHolder(view)

}