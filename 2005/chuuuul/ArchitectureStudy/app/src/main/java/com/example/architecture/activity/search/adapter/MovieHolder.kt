package com.example.architecture.activity.search.adapter

import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.text.Spannable
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.fromHtml
import androidx.core.text.toSpanned
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture.R
import com.example.architecture.data.model.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup


class MovieHolder(view: View) : RecyclerView.ViewHolder(view), MovieHolderContract.View {

    private val movieHolderPresenter = MovieHolderPresenter(this)

    override fun showMovieElement(movie: MovieModel) {
        movieHolderPresenter.bindMovie(movie)
    }

    override fun showMovieTitle(text: String) {
        itemView.tv_movie_title.text = text
    }

    override fun showMoviePubData(text: String) {
        itemView.tv_movie_pubDate.text = text
    }

    override fun showMovieRating(rating: Float) {
        itemView.ratingBar_movie_rating.rating = rating
    }

    override fun showMovieImage(imageUrl: String) {
        Glide.with(itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_loading_black_24dp)
            .error(R.drawable.image_loaderror).centerCrop().into(itemView.img_movie_Image)
    }
}