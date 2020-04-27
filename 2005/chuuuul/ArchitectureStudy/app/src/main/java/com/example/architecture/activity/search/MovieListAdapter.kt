package com.example.architecture.activity.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private val movies: ArrayList<MovieVO>) :
        RecyclerView.Adapter<MovieViewHolder>() {

    private lateinit var context: Context

    private val NO_IMAGE_URL = "https://ssl.pstatic.net/static/movie/2012/06/dft_img133x190.png"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemView.textView_movie_title.text = movies[position].title
        holder.itemView.textView_movie_pubDate.text = movies[position].pubDate

        holder.itemView.ratingBar_movie_rating.rating = movies[position].userRating

        setMovieImage(holder.itemView.imageView_movie_Image, movies[position].image)
    }

    private fun setMovieImage(imageView: ImageView, url: String?) {
        if (url != null) {
            Glide.with(context).load(url).fitCenter().error(R.drawable.image_loaderror).into(imageView)
        } else
            Glide.with(context).load(NO_IMAGE_URL).fitCenter().error(R.drawable.image_loaderror).into(imageView)
    }

}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
