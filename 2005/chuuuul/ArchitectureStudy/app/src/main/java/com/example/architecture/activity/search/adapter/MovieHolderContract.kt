package com.example.architecture.activity.search.adapter

import com.example.architecture.data.model.MovieModel

interface MovieHolderContract {
    interface View {
        fun showMovieElement(movie: MovieModel)

        fun showMovieTitle(text: String)
        fun showMoviePubData(text: String)
        fun showMovieRating(rating: Float)
        fun showMovieImage(imageUrl: String)
    }

    interface Presenter {

        fun bindMovie(movie: MovieModel)
        fun removeMarkupTag(html: String): String
        fun checkImageUrl(imageUrl: String): String
    }
}