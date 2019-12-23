package com.example.architecture_project.feature.movie

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture_project.R
import com.example.architecture_project.data.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.rv_item_iv_movie_image)
    val title: TextView = view.findViewById(R.id.rv_item_tv_movie_title)
    val userRating: RatingBar = view.findViewById(R.id.rv_item_rb_movie_rating)
    val pubDate: TextView = view.findViewById(R.id.rv_item_tv_movie_pubDate)
    val director: TextView = view.findViewById(R.id.rv_item_tv_movie_director)
    val actor: TextView = view.findViewById(R.id.rv_item_tv_movie_actor)

    fun bind(data: Movie) {
        Glide.with(itemView).load(data.image).into(image)
        title.setText(Html.fromHtml(data.title))
        userRating.rating = data.rating/2
        pubDate.text = data.pubDate
        director.text = data.director
        actor.text = data.actor
    }
}