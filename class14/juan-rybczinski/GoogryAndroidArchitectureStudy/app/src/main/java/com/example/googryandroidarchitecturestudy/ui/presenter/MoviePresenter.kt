package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract
import com.example.googryandroidarchitecturestudy.ui.contract.MovieListContract

class MoviePresenter(
    view: MovieListContract.View
) : MovieListPresenter(view), MovieContract.Presenter {

}