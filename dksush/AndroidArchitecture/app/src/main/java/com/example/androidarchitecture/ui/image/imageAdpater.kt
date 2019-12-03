package com.example.androidarchitecture.ui.image

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseImage
import com.example.androidarchitecture.ui.WebviewActivity
import kotlinx.android.synthetic.main.item_image.view.*

class imageAdpater(
    val items: List<ResponseImage>
) :
    RecyclerView.Adapter<imageAdpater.ImageHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class ImageHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: ResponseImage) {
            with(view) {
                image_title.text = model.title
                try {
                    Glide.with(this)
                        .load(model.thumbnail)
                        .into(image_thum)
                } catch (e: Exception) {
                    Log.v("imageAdpater", e.message)
                }


                setOnClickListener() {
                    Intent(context, WebviewActivity::class.java).apply {
                        putExtra("link", model.link)
                    }.run { context.startActivity(this) }
                }

            }
        }
    }

}