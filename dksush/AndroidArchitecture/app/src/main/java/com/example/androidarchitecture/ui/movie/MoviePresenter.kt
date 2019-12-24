package com.example.androidarchitecture.ui.movie

import com.example.androidarchitecture.data.repository.NaverRepoInterface
import com.example.androidarchitecture.data.response.MovieData
import com.example.androidarchitecture.ui.base.ItemContract

class MoviePresenter(
    private val view: ItemContract.View<MovieData>,
    private val repoInterface: NaverRepoInterface
) :
    ItemContract.Presenter {
    override fun requestList(text: String) {
        repoInterface.getMovie(text, 1, 10,
            success = {
                view.renderItems(it)
            }, fail = {
                view.errorToast(it.toString())

            })
    }
}