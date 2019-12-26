package com.example.architecturestudy.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.ui.startWebView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private val movieItem : MutableList<MovieItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieHolder(view).apply {
            itemView.setOnClickListener { v -> v.startWebView(movieItem[adapterPosition].link) }
        }
    }

    override fun getItemCount(): Int {
        return movieItem.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieItem[position])
    }

    fun update(movieList : List<MovieItem>) {
        this.movieItem.clear()
        this.movieItem.addAll(movieList)
        notifyDataSetChanged()
    }

    class MovieHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(item : MovieItem) {

            with(itemView) {
                movie_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                movie_director.text = item.director
                movie_actor.text = item.actor

                Glide.with(this).load(item.image).error(R.drawable.ic_launcher_background).into(movie_image)

                movie_rating.rating = item.rating
            }
        }
    }
}