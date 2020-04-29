package com.example.architecture.activity.search

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture.R
import com.example.architecture.vo.MovieVO
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
        holder.itemView.textView_movie_title.text = removeMarkupTag(movies[position].title)
        holder.itemView.textView_movie_pubDate.text = movies[position].pubDate

        holder.itemView.ratingBar_movie_rating.rating = movies[position].userRating

        setMovieImage(holder.itemView.imageView_movie_Image, movies[position].image)

        holder.itemView.setOnClickListener {
            openWebPage(movies[position].link)
        }
    }

    private fun setMovieImage(imageView: ImageView, imageUrl: String) {

        val url = if (imageUrl.isEmpty()) NO_IMAGE_URL else imageUrl

        Glide.with(context)
            .load(url).placeholder(R.drawable.ic_loading_black_24dp)
            .error(R.drawable.image_loaderror).centerCrop().into(imageView)
    }

    private fun removeMarkupTag(string: String): String {
        return string.replace("<b>", "").replace("</b>", "")
    }

    private fun openWebPage(urlString: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        context.startActivity(intent)
    }

}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
