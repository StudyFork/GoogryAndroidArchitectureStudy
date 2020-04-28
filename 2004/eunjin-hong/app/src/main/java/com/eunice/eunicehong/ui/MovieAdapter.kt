package com.eunice.eunicehong.ui

import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.databinding.ItemMovieBinding

@BindingAdapter("app:imageSrc")
fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("app:formattedText")
fun formattedText(textView: TextView, text: String) {
    val formatted = text.split("|")
        .map { it.trim() }
        .filter { it != "" }
        .joinToString(", ")

    textView.text = formatted.parseHTML()
}

private fun String.parseHTML(): Spanned =
    HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

class MovieAdapter(private val presenter: MoviePresenter) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return ViewHolder(binding, presenter)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(movieList[position])
        }
    }

    private fun addAllMovies(movies: Collection<Movie>) {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun setMovieList(movies: Collection<Movie>) {
        movieList.clear()
        addAllMovies(movies)
    }

    class ViewHolder(private val binding: ItemMovieBinding, private val presenter: MoviePresenter) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var movie: Movie

        fun bind(item: Movie) {
            movie = item

            binding.movie = movie
            binding.viewHolder = this@ViewHolder

        }

        fun showDetailWebPage() {
            presenter.showDetail(movie.link)
        }

    }
}