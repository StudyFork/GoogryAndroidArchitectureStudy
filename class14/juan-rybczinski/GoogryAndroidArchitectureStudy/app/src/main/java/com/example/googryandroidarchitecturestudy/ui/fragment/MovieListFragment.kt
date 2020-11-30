package com.example.googryandroidarchitecturestudy.ui.fragment

import androidx.viewbinding.ViewBinding
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.contract.MovieListContract
import com.example.googryandroidarchitecturestudy.ui.presenter.BasePresenter
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter

abstract class MovieListFragment<B : ViewBinding, P : BasePresenter> : BaseFragment<B, P>(),
    MovieListContract.View {
    protected abstract val presenter: P

    protected val movieAdapter = MovieAdapter {
        presenter.selectUrlItem(it)
    }

    override fun showMovieList(items: List<Movie>) {
        movieAdapter.setMovies(items)
    }
}