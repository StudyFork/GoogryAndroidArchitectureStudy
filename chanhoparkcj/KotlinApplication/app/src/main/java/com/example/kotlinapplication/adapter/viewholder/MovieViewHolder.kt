package com.example.kotlinapplication.adapter.viewholder

import android.os.Build
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.adapter.ListMovieAdapter
import com.example.kotlinapplication.model.MovieItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: MovieItem, listener: ListMovieAdapter.ItemListener?) {
        itemView.movie_item_layout.setOnClickListener {
            listener?.let {
                it.onMovieItemClick(item)
            }
        }
        if (item.image.isNotEmpty()) {
            Picasso.get()
                .load(item.image)
                .resize(300, 450)
                .into(itemView.movie_item_image)
        }
        itemView.movie_item_user_rating.rating = item.userRating.toFloat()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemView.movie_item_title.text = Html.fromHtml(item.title, 0)
            itemView.movie_item_pubDate.text = Html.fromHtml(item.pubDate, 0)
            itemView.movie_item_director.text = Html.fromHtml(item.director, 0)
            itemView.movie_item_actor.text = Html.fromHtml(item.actor, 0)
        } else {
            itemView.movie_item_title.text = item.title
            itemView.movie_item_pubDate.text = item.pubDate
            itemView.movie_item_director.text = item.director
            itemView.movie_item_actor.text = item.actor
        }


    }
}