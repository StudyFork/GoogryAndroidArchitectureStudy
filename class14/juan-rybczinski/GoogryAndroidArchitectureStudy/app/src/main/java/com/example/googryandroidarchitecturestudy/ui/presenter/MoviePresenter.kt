package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract
import com.example.googryandroidarchitecturestudy.ui.contract.MovieListContract

open class MoviePresenter(
    view: MovieListContract.View,
    repository: MovieRepository
) : MovieListPresenter(view, repository), MovieContract.Presenter {

}