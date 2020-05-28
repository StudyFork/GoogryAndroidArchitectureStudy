package com.example.architecture.activity.search.adapter

import android.content.Context
import com.example.architecture.data.model.MovieModel

interface MovieAdapterContract {

    interface View {

        fun showMovieList()
        fun showMovieWebPage(context: Context, link: String)
    }

    interface Presenter {
        fun setMovieList(movieList: List<MovieModel>)
        fun setMovieElement(holder: MovieHolder, position: Int)
        fun openWebPage(context: Context, position: Int)
    }
}