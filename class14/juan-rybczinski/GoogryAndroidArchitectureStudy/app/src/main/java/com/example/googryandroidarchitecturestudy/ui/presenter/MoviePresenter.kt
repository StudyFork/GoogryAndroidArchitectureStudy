package com.example.googryandroidarchitecturestudy.ui.presenter

import android.content.Context
import com.example.googryandroidarchitecturestudy.ui.contract.MovieContract
import com.example.googryandroidarchitecturestudy.ui.contract.MovieListContract

class MoviePresenter(
    view: MovieListContract.View,
    context: Context
) : MovieListPresenter(view, context), MovieContract.Presenter {

}