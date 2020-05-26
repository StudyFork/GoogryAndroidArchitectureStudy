package com.example.architecture.activity.search.adapter

import com.example.architecture.data.model.MovieModel
import com.example.architecture.util.ConstValue.Companion.NO_IMAGE_URL

class MovieHolderPresenter(
    private val view: MovieHolderContract.View
) : MovieHolderContract.Presenter {

    override fun bindMovie(movie: MovieModel) {
        view.showMovieTitle(removeMarkupTag(movie.title))
        view.showMoviePubData(movie.pubDate)
        view.showMovieRating(movie.userRating)
        view.showMovieImage(checkImageUrl(movie.image))
    }

    override fun removeMarkupTag(html: String): String {
        return html.replace("<b>", "").replace("</b>", "")
    }

    override fun checkImageUrl(imageUrl: String): String {
        return if (imageUrl.isBlank()) {
            NO_IMAGE_URL
        } else {
            imageUrl
        }
    }


}