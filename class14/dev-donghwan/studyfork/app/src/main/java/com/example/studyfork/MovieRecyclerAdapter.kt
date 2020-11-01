package com.example.studyfork

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studyfork.model.MovieSearchResponse

class MovieRecyclerAdapter : RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {
    private val items: ArrayList<MovieSearchResponse.MovieItem> = ArrayList()

    fun itemChange(vararg item: MovieSearchResponse.MovieItem) {
        this.items.clear()
        this.items.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    ) {
        private val image: ImageView = itemView.findViewById(R.id.iv_image)
        private val title: TextView = itemView.findViewById(R.id.tv_title)

        fun onBind(item: MovieSearchResponse.MovieItem) {
            this.title.text = item.title
            Glide.with(itemView).load(item.image).into(image)
        }
    }
}