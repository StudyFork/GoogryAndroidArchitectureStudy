package com.example.architecturestudy.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.ImageItems
import com.example.architecturestudy.ui.startWebView
import kotlinx.android.synthetic.main.item_image.view.*
import java.lang.Exception

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    private val imageItem : MutableList<ImageItems> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int {
        return imageItem.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(imageItem[position])
    }

    fun update(imageList : List<ImageItems>) {
        this.imageItem.clear()
        this.imageItem.addAll(imageList)
        notifyDataSetChanged()
    }

    class ImageHolder(view : View) : RecyclerView.ViewHolder(view) {

        lateinit var item: ImageItems

        init {
            itemView.setOnClickListener { view ->
                view.startWebView(item.link)
            }
        }

        fun bind(item : ImageItems) {
            this.item = item

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