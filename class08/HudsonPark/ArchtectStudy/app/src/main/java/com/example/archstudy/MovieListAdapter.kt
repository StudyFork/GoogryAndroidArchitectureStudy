package com.example.archstudy

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class MovieListAdapter(private var movieList: List<Item>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(movieList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val layoutItem = itemView.findViewById<LinearLayout>(R.id.layoutItem)
        private val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbNail)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val ratingMovie = itemView.findViewById<RatingBar>(R.id.ratingMovie)
        private val tvReleaseYear = itemView.findViewById<TextView>(R.id.tvReleaseYear)
        private val tvDirector = itemView.findViewById<TextView>(R.id.tvDirector)
        private val tvActors = itemView.findViewById<TextView>(R.id.tvActors)
        private var clickPosition = -1

        fun bind(data: Item) {

            with(data) {
                Log.d("img","image : $image, title : $title")
                if (image != null) {
                    Log.d("img","Image loaded")
                    Glide.with(itemView.context).load(image).override(600, 1000).into(ivThumbnail)
                } else if(!(data.image.contains("http://"))){ //error using image.isEmpty()
                    Log.d("img","No_Img")
                    Glide.with(itemView.context).load(R.drawable.no_image).override(600, 1000).into(ivThumbnail)
                }
                tvTitle.text = title
                ratingMovie.rating = userRating.toFloat() / 2
                tvReleaseYear.text = pubDate
                tvDirector.text = director
                tvActors.text = actor
            }

            // 아이템 클릭시
            layoutItem.setOnClickListener {
                clickPosition = adapterPosition
                Log.d("click","눌려진 아이템 : $clickPosition")
            }
        }
    }
}