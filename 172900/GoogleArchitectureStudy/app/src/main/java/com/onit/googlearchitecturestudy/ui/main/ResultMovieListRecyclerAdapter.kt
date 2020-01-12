package com.onit.googlearchitecturestudy.ui.main

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onit.googlearchitecturestudy.Movie
import com.onit.googlearchitecturestudy.R
import kotlinx.android.synthetic.main.holder_movie.view.*

class ResultMovieListRecyclerAdapter(
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<ResultMovieListRecyclerAdapter.MovieViewHolder>() {

    private val list: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_movie, parent, false)

        return MovieViewHolder(itemView, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]
        holder.bind(movie)
    }

    fun setMovieList(newMovieList: List<Movie>) {
        with(list) {
            clear()
            addAll(newMovieList)
        }
        notifyDataSetChanged()
    }

    fun getMovieURL(position: Int): String {
        return list[position].link
    }

    inner class MovieViewHolder(itemView: View, clickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                clickListener(adapterPosition)
            }
        }

        fun bind(movie: Movie) {
            with(itemView) {
                titleTextView.text = movie.title.removeTag()
                gradeRatingBar.rating = movie.userRating / 2f
                releaseDateTextView.text = movie.releaseDate
                directorTextView.text = movie.director
                actorsTextView.text = movie.actor
                Glide.with(this).load(movie.posterImage).into(posterImageView)
            }
        }
    }

    private fun String.removeTag(): String {
        return Html.fromHtml(this).toString()
    }
}
