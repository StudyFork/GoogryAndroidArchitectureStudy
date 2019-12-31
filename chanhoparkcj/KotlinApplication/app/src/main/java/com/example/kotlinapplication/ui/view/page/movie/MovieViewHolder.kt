package com.example.kotlinapplication.ui.view.page.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.MovieItem
import com.example.kotlinapplication.extension.getHtmlText
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(parent: ViewGroup, listener: (MovieItem) -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_movie,
        parent,
        false
    )
) {
    private lateinit var item: MovieItem

    init {
        itemView.setOnClickListener { listener(item) }
    }

    fun bind(item: MovieItem) {
        this.item = item
        with(itemView) {
            if (item.image.isNotEmpty()) {
                Picasso.get()
                    .load(item.image)
                    .resize(300, 450)
                    .into(imageview_movie)
            }

            ratingbar_movie.rating = item.userRating
            textview_movie_title.text = item.title.getHtmlText()
            textview_movie_pubDate.text = item.pubDate.getHtmlText()
            textview_movie_director.text = item.director.getHtmlText()
            textview_movie_actor.text = item.actor.getHtmlText()

        }


    }
}