package com.example.googryandroidarchitecturestudy.ui.fragment

import androidx.viewbinding.ViewBinding
import com.example.googryandroidarchitecturestudy.ui.contract.MovieListContract
import com.example.googryandroidarchitecturestudy.ui.presenter.BasePresenter

abstract class MovieListFragment<B : ViewBinding, P : BasePresenter> :
    MovieListContract.View {
    protected abstract val presenter: P
}