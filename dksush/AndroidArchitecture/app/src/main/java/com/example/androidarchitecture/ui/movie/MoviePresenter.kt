package com.example.androidarchitecture.ui.movie

import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.MovieData
import com.example.androidarchitecture.ui.base.NaverContract

class MoviePresenter(
    private val view: NaverContract.View<MovieData>,
    private val RepoInterface: NaverRepoInterface
) :
    NaverContract.Presenter<MovieData> {
    override fun requestList(text: String) {
        RepoInterface.getMovie(text, 1, 10,
            success = {
                view.renderItems(it)
            }, fail = {
                view.errorToast(it.toString())

            })
    }
}