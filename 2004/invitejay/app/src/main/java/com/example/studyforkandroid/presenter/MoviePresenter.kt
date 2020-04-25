package com.example.studyforkandroid.presenter

import com.example.studyforkandroid.data.source.MovieRepositoryImpl

class MoviePresenter : MovieContract.Preseneter {

    private var view: MovieContract.View? = null
    private var repository: MovieRepositoryImpl? = null

    override fun setView(view: MovieContract.View) {
        this.view = view
        this.repository = MovieRepositoryImpl
    }

    override fun loadItem(title: String) {
        MovieRepositoryImpl.getRemoteMovieList(
            title,
            onSuccess = { movieList ->
                view?.setItems(movieList)
            },
            onFailure = { e ->
                e.printStackTrace()
            })
    }
}