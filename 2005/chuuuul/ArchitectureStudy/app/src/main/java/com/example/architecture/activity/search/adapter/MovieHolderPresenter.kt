package com.example.architecture.activity.search.adapter

import com.example.architecture.data.model.MovieModel
import com.example.architecture.util.ConstValue.Companion.NO_IMAGE_URL
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

class MovieHolderPresenter(
    private val view: MovieHolderContract.View
) : MovieHolderContract.Presenter {

    override fun bindMovie(movie: MovieModel) {
        view.showMovieTitle(removeHtmlTag(movie.title))
        view.showMoviePubData(movie.pubDate)
        view.showMovieRating(movie.userRating)
        view.showMovieImage(checkImageUrl(movie.image))
    }

    private fun removeHtmlTag(html: String): String {
        val escapedHtml = StringEscapeUtils.unescapeHtml4(html)
        return Jsoup.parse(escapedHtml).text()
    }

    private fun checkImageUrl(imageUrl: String): String {
        return if (imageUrl.isBlank()) {
            NO_IMAGE_URL
        } else {
            imageUrl
        }
    }


}