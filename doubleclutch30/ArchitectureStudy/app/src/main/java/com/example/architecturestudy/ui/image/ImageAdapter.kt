package com.example.architecturestudy.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.R
import com.example.architecturestudy.model.BlogItems
import com.example.architecturestudy.model.ImageData
import com.example.architecturestudy.model.ImageItems
import kotlinx.android.synthetic.main.item_image.view.*
import java.lang.Exception

class ImageAdapter(val items : List<ImageItems>) : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    private val imageList : MutableList<ImageItems> = mutableListOf()


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

    fun upDate(imageList : List<ImageItems>) {
        this.imageList.clear()
        this.imageList.addAll(imageList)
        notifyDataSetChanged()
    }

    class ImageHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(item : ImageItems) {
            with(itemView) {

                try {
                    Glide.with(this).load(item.thumbnail).into(img_image)
                } catch (e : Exception) {
                    error(message = e.toString())
                }
            }
        }

    }
}