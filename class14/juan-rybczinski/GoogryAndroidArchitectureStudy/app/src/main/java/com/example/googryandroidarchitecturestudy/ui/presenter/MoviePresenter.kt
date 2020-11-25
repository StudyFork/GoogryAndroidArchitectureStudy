package com.example.googryandroidarchitecturestudy.ui.presenter

import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract

class MoviePresenter(
    view: MovieContract.View,
    repository: MovieRepository
) : BasePresenter(view, repository), MovieContract.Presenter {

}