package com.example.study.presenter

interface MoviePresenter : MovieContract.Presenter {

    override fun requestMovieList(query: String)

    fun doSearch(query: String)

    fun showQueryEmpty()
}
