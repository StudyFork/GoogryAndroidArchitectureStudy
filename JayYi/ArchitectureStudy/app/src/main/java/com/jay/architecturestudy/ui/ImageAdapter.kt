package com.jay.architecturestudy.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Image
import kotlinx.android.synthetic.main.list_item_image.view.*
import kotlinx.android.synthetic.main.list_item_movie.view.*

internal class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageHolder>() {
    private var data = arrayListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_image, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(images: List<Image>) {
        data.clear()
        data.addAll(images)
        notifyDataSetChanged()
    }

}

internal class ImageHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {
    private lateinit var model: Image

    fun bind(model: Image) {
        this.model = model

        with(view) {
            image_title.text = model.title

            try {
                Glide.with(this)
                    .load(model.thumbnail)
                    .into(image_thumbnail)
            } catch (e: Exception) {
                Log.e("MovieAdapter", "error=${e.message}")
            }

            setOnClickListener {
                val context = view.context
                Intent(context, WebViewActivity::class.java).apply {
                    putExtra(WebViewActivity.EXTRA_URL, model.link)
                }.run {
                    context.startActivity(this)
                }
            }
        }
    }
}