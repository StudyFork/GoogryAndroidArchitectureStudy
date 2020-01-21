package com.example.study.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.study.R
import com.example.study.data.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        val holder = MovieViewHolder(view)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClickListener(movies[holder.adapterPosition])
        }

        return holder
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
                    .into(iv_movie_image)
                tv_title.text = movie.title
                rb_movie_rating.rating = movie.userRating.toFloat() / 2
                tv_pubdate.text = movie.pubDate
                tv_director.text = movie.director
                tv_actor.text = movie.actor
            }
            Log.d("bind()", this.adapterPosition.toString())

        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(movie: Movie)
    }
}


