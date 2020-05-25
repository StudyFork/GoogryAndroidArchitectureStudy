package com.example.architecture.activity.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture.R
import com.example.architecture.data.model.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder(view: View) : RecyclerView.ViewHolder(view), MovieHolderContract.View {

    private val movieHolderPresenter = MovieHolderPresenter(this)

    override fun onBind(movie: MovieModel) {
        setMovieTitle(movie.title)
        setMoviePubData(movie.pubDate)
        setMovieRating(movie.userRating)
        setMovieImage(movie.image)
    }

    override fun setMovieTitle(text: String) {
        itemView.tv_movie_title.text = movieHolderPresenter.removeMarkupTag(text)
    }

    override fun setMoviePubData(text: String) {
        itemView.tv_movie_pubDate.text = text
    }

    override fun setMovieRating(rating: Float) {
        itemView.ratingBar_movie_rating.rating = rating
    }

    override fun setMovieImage(imageUrl: String) {
        Glide.with(itemView.context)
            .load(movieHolderPresenter.checkImageUrl(imageUrl))
            .placeholder(R.drawable.ic_loading_black_24dp)
            .error(R.drawable.image_loaderror).centerCrop().into(itemView.img_movie_Image)
    }
}