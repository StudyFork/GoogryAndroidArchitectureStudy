package com.olaf.nukeolaf

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movies_rv.view.*

class MovieAdapter(
    private val itemListener: MovieItemListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies: List<MovieItem> = ArrayList()

    fun setMovies(list: List<MovieItem>) {
        this.movies = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieItem) {
            itemView.apply {
                movie_title.text = item.title.htmlToString()
                movie_subtitle.text = item.subtitle
                movie_pub_date.text = item.pubDate
                movie_director.text = item.director.addCommas("감독 : ")
                movie_actor.text = item.actor.addCommas("출연진 : ")
                movie_rating.numStars = 5
                movie_rating.rating = item.userRating / 2
                setOnClickListener {
                    itemListener.onMovieItemClick(item)
                }
                Glide.with(this)
                    .load(item.image)
                    .into(this.movie_image)
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
            return if (this.isNotEmpty()) {
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
}