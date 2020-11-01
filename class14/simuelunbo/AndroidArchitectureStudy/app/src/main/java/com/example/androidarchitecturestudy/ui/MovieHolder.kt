package com.example.androidarchitecturestudy.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecturestudy.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(movie: Movie){
        itemView.tv_movie_title.text = removeUselessTitle(movie.title)
        itemView.tv_pub_date.text = movie.pubDate
        itemView.tv_director_name.text = movie.director
        itemView.tv_movie_rate.text = movie.userRating
        Glide.with(itemView).load(movie.image).into(itemView.iv_movie)
    }
    private fun removeUselessTitle(title: String?): String?{
        return title?.replace("<b>", "")?.replace("</b>", "")
    }
}