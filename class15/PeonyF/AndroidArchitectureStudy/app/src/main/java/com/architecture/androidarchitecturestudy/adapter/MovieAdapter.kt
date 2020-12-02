package com.architecture.androidarchitecturestudy.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.databinding.MovieItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val modelList = ArrayList<Movie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = modelList.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(this.modelList[position])
    }

    fun setMovieList(items: List<Movie>) {
        this.modelList.clear()
        this.modelList.addAll(processMovieItemString(items))
        notifyDataSetChanged()
    }

    private fun processMovieItemString(movies: List<Movie>): List<Movie> {
        return movies.map {
            it.copy(
                title = it.title?.htmlToString(),
                director = it.director?.addCommas("감독 : "),
                actor = it.actor?.addCommas("출연진 : "),
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