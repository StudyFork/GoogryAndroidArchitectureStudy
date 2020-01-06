package com.example.archstudy

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

class MovieListAdapter(private val movieList: List<Items>) :
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
        private val nullMessage = "데이터가 없습니다."
        private var clickPosition = -1

        fun bind(data : Items){

            Glide.with(itemView.context).load(data.image).into(ivThumbnail)
            tvTitle.text = data.title
            ratingMovie.numStars = data.userRating
            tvReleaseYear.text = SimpleDateFormat("yyyy").format(data.pubDate) ?: nullMessage
            tvDirector.text = data.director
            tvActors.text = data.actor

            // 아이템 클릭시
            layoutItem.setOnClickListener {
                clickPosition = adapterPosition
            }
        }
    }

}