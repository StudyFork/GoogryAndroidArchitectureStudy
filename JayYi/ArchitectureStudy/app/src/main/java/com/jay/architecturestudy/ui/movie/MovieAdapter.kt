package com.jay.architecturestudy.ui.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Movie
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.util.startWebView
import kotlinx.android.synthetic.main.list_item_movie.view.*

internal class MovieAdapter : BaseAdapter<Movie, MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieHolder(view)
    }
}

internal class MovieHolder(
    view: View
) : BaseViewHolder<Movie>(view) {
    lateinit var item: Movie

    init {
        itemView.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }

    override fun bind(item: Movie) {
        this.item = item

        with(itemView) {
            movie_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            movie_director.text = item.director
            movie_actor.text = item.actor

            try {
                Glide.with(this)
                    .load(item.image)
                    .into(movie_poster)
            } catch (e: Exception) {
                Log.e("MovieAdapter", "error=${e.message}")
            }

            user_rating.rating = item.userRating
        }
    }
}