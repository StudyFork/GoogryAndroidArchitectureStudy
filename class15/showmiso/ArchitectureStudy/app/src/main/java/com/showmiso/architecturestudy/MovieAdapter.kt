package com.showmiso.architecturestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.showmiso.architecturestudy.api.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movies: MutableList<MovieModel.Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    fun setMovieList(movies: List<MovieModel.Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieModel.Movie) {
            itemView.tv_title.text = getTitleString(movie.title)
            itemView.tv_sub_title.text = movie.subtitle
            itemView.tv_pub_date.text = movie.pubDate
            itemView.tv_director.text = getDirectorString(movie.director)
            Glide.with(itemView)
                .load(movie.image)
                .into(itemView.img_photo)
        }

        private fun getTitleString(title: String?): String? {
            return title?.replace("<b>", "")?.replace("</b>", "")
        }

        private fun getDirectorString(director: String?): String? {
            return director?.let {
                if (it.isEmpty()) {
                    ""
                } else {
                    it.substring(0, director.length - 1).replace("|", ", ")
                }
            }
        }
    }

}