package com.example.architecture.activity.search.adapter

import com.example.architecture.data.model.MovieModel

class MovieAdapterPresenter(
    private val view: MovieAdapterContract.View
) : MovieAdapterContract.Presenter {

    private val movieList = mutableListOf<MovieModel>()

    override fun getMovie(position: Int): MovieModel {
        return movieList[position]
    }

    override fun getMovieCount(): Int = movieList.count()

    override fun addNewItems(movieList: List<MovieModel>) {
        if (this.movieList.isNotEmpty()) {
            this.movieList.clear()
        }
        this.movieList.addAll(movieList)
        view.updateMovieList()
    }



}