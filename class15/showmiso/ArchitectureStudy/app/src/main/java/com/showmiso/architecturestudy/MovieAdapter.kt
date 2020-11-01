package com.showmiso.architecturestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.showmiso.architecturestudy.api.Model
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movies: List<Model.Movie> = ArrayList()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Model.Movie) {
//            val movie: Model.Movie = itemView.tag as Model.Movie
            itemView.tv_title.text = getTitleString(movie.title)
            itemView.tv_sub_title.text = movie.subtitle
            itemView.tv_pub_date.text = movie.pubDate
            itemView.tv_director.text = getDirectorString(movie.director)
            Glide.with(itemView)
                .load(movie.image)
                .into(itemView.img_photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
//        holder.itemView.tag = movie
        holder.bind(movie)
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

    override fun getItemCount(): Int {
        return movies.size
    }
}