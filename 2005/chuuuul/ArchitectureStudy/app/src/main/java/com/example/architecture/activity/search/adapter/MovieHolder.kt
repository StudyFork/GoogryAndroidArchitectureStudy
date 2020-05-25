package com.example.architecture.activity.search.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture.R
import com.example.architecture.data.model.MovieModel
import com.example.architecture.util.ConstValue.Companion.NO_IMAGE_URL
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder(view: View) : RecyclerView.ViewHolder(view), MovieHolderContract.View {

    fun onBind(movie: MovieModel) {
        itemView.tv_movie_title.text = removeMarkupTag(movie.title)
        itemView.tv_movie_pubDate.text = movie.pubDate

        itemView.ratingBar_movie_rating.rating = movie.userRating

        setMovieImage(itemView.img_movie_Image, movie.image)

    }

    private fun removeMarkupTag(html: String): String {
        return html.replace("<b>", "").replace("</b>", "")
    }

    private fun setMovieImage(imageView: ImageView, imageUrl: String) {
        val url = if (imageUrl.isBlank()) {
            NO_IMAGE_URL
        } else {
            imageUrl
        }
        Glide.with(imageView.context)
            .load(url).placeholder(R.drawable.ic_loading_black_24dp)
            .error(R.drawable.image_loaderror).centerCrop().into(imageView)
    }

}