package com.example.architecture.activity.search.adapter

import android.content.Context
import com.example.architecture.data.model.MovieModel

class MovieAdapterPresenter(
    private val view: MovieAdapterContract.View
) : MovieAdapterContract.Presenter {

    private val movieList = mutableListOf<MovieModel>()

    internal fun getMovieCount(): Int {
        return movieList.count()
    }

    override fun setMovieList(movieList: List<MovieModel>) {
        if (this.movieList.isNotEmpty()) {
            this.movieList.clear()
        }
        this.movieList.addAll(movieList)
        view.showMovieList()
    }

    override fun setMovieElement(holder: MovieHolder, position: Int) {
        val movie = movieList[position]
        holder.showMovieElement(movie)
    }

    override fun openWebPage(context: Context, position: Int) {
        val link = movieList[position].link
        view.showMovieWebPage(context, link)
    }
}