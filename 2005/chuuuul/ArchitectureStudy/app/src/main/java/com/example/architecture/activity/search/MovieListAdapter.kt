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
import com.example.architecture.ConstValue.Companion.NO_IMAGE_URL
import com.example.architecture.R
import com.example.architecture.data.model.MovieVO
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = ArrayList<MovieVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        val viewHolder = MovieViewHolder(view)

        view.setOnClickListener {
            openWebPage(it.context, movies[viewHolder.layoutPosition].link)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movies[position]

        holder.itemView.tv_movie_title.text = removeMarkupTag(movie.title)
        holder.itemView.tv_movie_pubDate.text = movie.pubDate

        holder.itemView.ratingBar_movie_rating.rating = movie.userRating

        setMovieImage(holder.itemView.img_movie_Image, movie.image)


    }

    fun addNewItems(movieList : ArrayList<MovieVO>){
        if (movies.isNotEmpty()) {
            movies.clear()
        }

        movies.addAll(movieList)
        notifyDataSetChanged()
    }


    private fun setMovieImage(imageView: ImageView, imageUrl: String) {

        val url = if (imageUrl.isBlank()) NO_IMAGE_URL else imageUrl

        Glide.with(imageView.context)
            .load(url).placeholder(R.drawable.ic_loading_black_24dp)
            .error(R.drawable.image_loaderror).centerCrop().into(imageView)
    }

    private fun removeMarkupTag(string: String): String {
        return string.replace("<b>", "").replace("</b>", "")
    }

    private fun openWebPage(context : Context, urlString: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        context.startActivity(intent)
    }

}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
