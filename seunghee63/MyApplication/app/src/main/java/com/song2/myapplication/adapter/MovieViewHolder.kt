package com.song2.myapplication.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.song2.myapplication.R
import com.song2.myapplication.data.MovieData

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.iv_rv_movie_item_image)
    val title: TextView = view.findViewById(R.id.tv_rv_movie_item_title)
    val rating: RatingBar = view.findViewById(R.id.rb_rv_movie_item_rating)
    val pubDate: TextView = view.findViewById(R.id.tv_rv_movie_item_pub_date)
    val actor: TextView = view.findViewById(R.id.tv_rv_movie_item_actor)


    fun onBind(movieData: MovieData) {
        title.text = movieData.title
        pubDate.text = movieData.pubDate
        actor.text = movieData.actor

//        Glide.with(itemView.context).load(movieData.image)
//            .transform(RoundedCorners(180))
//            .transition(DrawableTransitionOptions.withCrossFade()) to image
    }
}