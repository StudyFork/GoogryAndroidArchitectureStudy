package com.example.architecture.activity.search.adapter

import com.example.architecture.data.model.MovieModel

interface MovieHolderContract {
    interface View {
        fun onBind(movie: MovieModel)

        fun setMovieTitle(text: String)
        fun setMoviePubData(text: String)
        fun setMovieRating(rating: Float)
        fun setMovieImage(imageUrl: String)
    }

    interface Presenter {

        fun removeMarkupTag(html: String): String
        fun checkImageUrl(imageUrl: String): String
    }
}