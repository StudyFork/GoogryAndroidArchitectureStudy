package com.olaf.nukeolaf.ui

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.databinding.ItemMoviesRvBinding

class MovieAdapter : RecyclerView.Adapter<MovieItemViewHolder>() {

    private val movies = mutableListOf<MovieItem>()

    fun setMovies(list: List<MovieItem>) {
        this.movies.apply {
            clear()
            addAll(processMovieItemString(list))
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesRvBinding.inflate(inflater, parent, false)
        return MovieItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    private fun processMovieItemString(movies: List<MovieItem>): List<MovieItem> {
        return movies.map {
            it.copy(
                title = it.title.htmlToString(),
                subtitle = it.subtitle.htmlToString(),
                director = it.director.addCommas("감독 : "),
                actor = it.actor.addCommas("출연진 : "),
                userRating = it.userRating / 2
            )
        }
    }

    private fun String.htmlToString(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(this).toString()
        }
    }

    private fun String.addCommas(prefix: String): String {
        return if (this.isNotBlank()) {
            this.substring(0, this.length - 1)
                .split("|")
                .joinToString(
                    prefix = prefix,
                    separator = ", "
                )
        } else {
            this
        }

    }
}