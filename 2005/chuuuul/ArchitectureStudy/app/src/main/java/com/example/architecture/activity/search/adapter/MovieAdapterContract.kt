package com.example.architecture.activity.search.adapter

import android.content.Context
import com.example.architecture.data.model.MovieModel

interface MovieAdapterContract {

    interface View {

        fun updateMovieList()
        fun showMovieWebPage(context: Context, link: String)
    }

    interface Presenter {

        fun getMovie(position: Int): MovieModel
        fun getMovieCount(): Int
        fun addNewItems(movieList: List<MovieModel>)
    }
}