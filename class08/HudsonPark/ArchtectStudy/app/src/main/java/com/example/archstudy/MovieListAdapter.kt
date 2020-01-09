package com.example.archstudy

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieListAdapter(private var movieList: List<Item>, @Nullable private var listener: ItemClickListener) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movieList[position], listener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val layoutItem = itemView.findViewById<ConstraintLayout>(R.id.layoutItem)
        private val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbNail)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val ratingMovie = itemView.findViewById<RatingBar>(R.id.ratingMovie)
        private val tvReleaseYear = itemView.findViewById<TextView>(R.id.tvReleaseYear)
        private val tvDirector = itemView.findViewById<TextView>(R.id.tvDirector)
        private val tvActors = itemView.findViewById<TextView>(R.id.tvActors)

        fun bind(data: Item, listener: ItemClickListener) {

            with(data) {
                Log.d("img", "image : $image, title : $title")
                loadImage(image)
                setTitle(title)
                ratingMovie.rating = userRating.toFloat() / 2
                tvReleaseYear.text = pubDate
                setDirector(director)
                setActors(actor)


                // 아이템 클릭 시 호출될 커스텀 리스너 정의
                layoutItem.setOnClickListener {
                    listener.onItemClick(link)
                }
            }

        }

        private fun setTitle(title: String) {
            tvTitle.text = title
        }

        private fun setDirector(director: String) {
            tvDirector.text = director
        }

        private fun setActors(actors: String) {
            tvActors.text = actors
        }

        private fun loadImage(image: String) {

            if (image.trim().isNotEmpty()) {

                Glide.with(itemView.context)
                    .load(image)
                    .override(600, 1000)
                    .into(ivThumbnail)
            } else {

                Glide.with(itemView.context)
                    .load(R.drawable.no_image)
                    .override(600, 1000)
                    .into(ivThumbnail)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(url: String)
    }

}