package com.olaf.nukeolaf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movies_rv.view.*

class MovieAdpater(
    private var movies: ArrayList<MovieItem>
) : RecyclerView.Adapter<MovieAdpater.ViewHolder>() {

    fun setMovies(list: ArrayList<MovieItem>) {
        this.movies = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdpater.ViewHolder {
        val inflatedView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movies_rv, parent)
        return (ViewHolder(inflatedView))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view = itemView

        fun bind(item: MovieItem, position: Int) {
            val director = "감독 : ${item.director}"
            val actor = "출연진 : ${item.actor}"
            view.apply {
                movie_title.text = item.title
                movie_subtitle.text = item.subtitle
                movie_pub_date.text = item.pubDate
                movie_director.text = director
                movie_actor.text = actor
            }
        }

    }
}