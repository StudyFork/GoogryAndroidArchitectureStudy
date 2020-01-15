package com.example.study.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.data.model.Movie
import kotlinx.android.synthetic.main.movie_view.view.*


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()
    private var onItemClickListener : OnItemClickListener?  = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_view, parent, false)
        val holder = MovieViewHolder(view)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClickListener(movies[holder.adapterPosition])
        }
        return MovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = object :
            OnItemClickListener {
            override fun onItemClickListener(movie: Movie) {
                listener(movie)
            }
        }
    }

    fun setItem(item: List<Movie>) {
        movies.clear()
        movies.addAll(item)
        notifyDataSetChanged()
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                Glide.with(context)
                    .load(movie.image)
                    .into(image_view)
                title_view.text = movie.title
                rating_bar.rating = movie.userRating.toFloat() / 2
                pubdate_view.text = movie.pubDate
                director_view.text = movie.director
                actor_view.text = movie.actor
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(movie: Movie)
    }
}


