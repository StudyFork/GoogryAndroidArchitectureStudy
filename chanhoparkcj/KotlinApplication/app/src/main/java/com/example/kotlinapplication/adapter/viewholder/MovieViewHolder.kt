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

        with(itemView) {
            if (item.image.isNotEmpty()) {
                Picasso.get()
                    .load(item.image)
                    .resize(300, 450)
                    .into(movie_item_image)
            }
            movie_item_layout.setOnClickListener {
                listener?.let {
                    it.onMovieItemClick(item)
                }
            }
            movie_item_user_rating.rating = item.userRating.toFloat()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                movie_item_title.text = Html.fromHtml(item.title, 0)
                movie_item_pubDate.text = Html.fromHtml(item.pubDate, 0)
                movie_item_director.text = Html.fromHtml(item.director, 0)
                movie_item_actor.text = Html.fromHtml(item.actor, 0)
            } else {
                movie_item_title.text = item.title
                movie_item_pubDate.text = item.pubDate
                movie_item_director.text = item.director
                movie_item_actor.text = item.actor
            }
        }


    }
}